package Authentication;

import Model.Roles.Gender;
import Model.Roles.UserType;
import Model.Shared.User;
import Model.UserFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The AccountManager class handles user authentication and password management.
 * It reads and updates user data from a CSV file.
 */
public class AccountManager {

    /** Path to the CSV file storing user login data. */
    private static final String FILE_PATH = "src/Data/Assets/userlogin.csv";

    /**
     * Authenticates a user by verifying the hospital ID and password.
     * If the user's first login is detected, prompts for a password change.
     *
     * @param hospID   the hospital ID of the user
     * @param password the password of the user
     * @return the authenticated User object, or {@code null} if authentication fails
     */
    public User login(String hospID, String password) {
        List<String[]> userDatabase = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            // Read header and skip it
            String header = reader.readLine();
            userDatabase.add(header.split(","));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String userHosID = values[0].trim();
                String userPassword = values[1].trim();
                UserType userRole = UserType.valueOf(values[2].trim().toUpperCase());
                String userName = values[3].trim();
                String userGender = values[4].trim();
                int userAge = Integer.parseInt(values[5].trim());
                String initialLogin = values[6].trim();

                if (userHosID.equals(hospID) && userPassword.equals(password)) {
                    System.out.println("Login successful for user, Welcome back " + userName);

                    if (initialLogin.equals("TRUE")) {
                        // Prompt user to change password on first login
                        System.out.println("Please change your password, as we notice that this is your first login");
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter your new password: ");
                        String newPassword = scanner.nextLine();
                        changePassword(hospID, newPassword);
                    }

                    // Return the authenticated user object
                    return UserFactory.createUser(userRole, hospID, password, userName, Gender.valueOf(userGender.toUpperCase()), userAge);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV database: " + e.getMessage());
        }
        return null;
    }

    /**
     * Updates the password of a user in the CSV file.
     * Also updates the initial login flag if it was set to true.
     *
     * @param hospID      the hospital ID of the user
     * @param newPassword the new password to set
     */
    public static void changePassword(String hospID, String newPassword) {
        List<String[]> fileContent = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            // Read header and store it
            String header = reader.readLine();
            fileContent.add(header.split(","));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                // Update the password for the matching hospital ID
                if (values[0].trim().equalsIgnoreCase(hospID)) {
                    values[1] = newPassword;

                    // If this is the user's first login, reset the initial login flag
                    if (values[6].trim().equalsIgnoreCase("TRUE")) {
                        values[6] = "FALSE";
                    }
                }

                fileContent.add(values);
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
            return;
        }

        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] values : fileContent) {
                writer.write(String.join(",", values));
                writer.newLine();
            }
            System.out.println("Password successfully changed.");
        } catch (IOException e) {
            System.out.println("Error writing to the User login CSV: " + e.getMessage());
        }
    }
}

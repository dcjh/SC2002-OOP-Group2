package App;

import Authentication.AccountManager;
import Model.Shared.User;
import java.util.Scanner; 
import Model.MenuFactory;
import View.UserMainView;

/**
 * The entry point for the Hospital Management System application.
 * Handles user interactions for login, password change, and system exit.
 */
public class App {
    
    /**
     * The main method of the application.
     * Provides a menu for user interaction and delegates operations such as login and password change.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String args[]) {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        // AccountManager handles user authentication and password management
        AccountManager accountManager = new AccountManager();

        while (option != 3) {
            // Display the main menu
            System.out.print("Welcome to Hospital Management System\n");
            System.out.print("1. Login\n");
            System.out.print("2. Change Password\n");
            System.out.print("3. Exit\n");
            System.out.print("Select an option: ");

            // Read user input
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Process user input
            switch (option) {
                case 1:
                    // Login functionality
                    System.out.print("Enter your hospital ID: ");
                    String hosID = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    // Authenticate the user
                    User user = accountManager.login(hosID, password);
                    if (user != null) {
                        // Display the main menu for the authenticated user
                        UserMainView mainPage = MenuFactory.getMainView(user);
                        mainPage.menu(hosID);
                    } else {
                        // Invalid login attempt
                        System.out.println("Invalid credentials. Login is denied. Try again.");
                    }
                    break;

                case 2:
                    // Change password functionality
                    System.out.print("Enter your hospital ID: ");
                    String hosIDChangePassword = scanner.nextLine();

                    System.out.print("Enter your new password: ");
                    String newPassword = scanner.nextLine();

                    // Update the user's password
                    accountManager.changePassword(hosIDChangePassword, newPassword);
                    System.out.println("Password changed successfully.");
                    break;

                case 3:
                    // Exit the system
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    // Handle invalid menu options
                    System.out.println("Invalid option. Please select a valid option.");
                    break;
            }
        }

        // Close the scanner to release resources
        scanner.close();
    }
}

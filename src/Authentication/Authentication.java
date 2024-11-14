package Authentication;

import Patient.Patient;
import Pharmacist.Pharmacist;
import Administrator.Administrator;

import java.io.*;
import java.util.*;

import Doctor.Doctor;
import User.User;

public class Authentication {
    private static final String USER_LOGIN = "SC2002-OOP-Group2/userlogin.csv";

    public User authenticate(String hospID, String password) {
        List<String[]> userLogin = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_LOGIN))) {
            String header = reader.readLine();
            userLogin.add(header.split(","));
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String userHosID = values[0].trim();
                String userPassword = values[1].trim();
                String userRole = values[2].trim();
                String userName = values[3].trim();
                String userGender = values[4].trim();
                int userAge = Integer.parseInt(values[5].trim());
                String initialLogin = values[6].trim();

                if (userHosID.equals(hospID) && userPassword.equals(password)) {
                    System.out.println("Login successful for user, Welcome back " + userName);

                    if (initialLogin.equals("TRUE")) {
                        System.out.println("Please change your password, as we notice that this is your first login");
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter your new password: ");
                        String newPassword = scanner.nextLine();
                        changePassword(hospID,newPassword);
                    }
                    return createUser(hospID, password, userRole, userName, userGender, userAge);
                }

            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV database:" + e.getMessage());
        }
        return null;
    }


    public static void changePassword(String hospID, String newPassword) {
        List<String[]> fileContent = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_LOGIN))){
            String header = reader.readLine();
            fileContent.add(header.split(","));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (values[0].trim().equalsIgnoreCase(hospID)) {
                    values[1] = newPassword;
                }

                if (values[0].trim().equalsIgnoreCase(hospID) && values[6].trim().equalsIgnoreCase("TRUE")) {
                    values[6] = "FALSE";
                }
                fileContent.add(values);
            }


        }
        catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
            return;
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_LOGIN))) {
            for (String[] values : fileContent) {
                writer.write(String.join(",", values));
                writer.newLine();
            }
            System.out.println("Password successfully changed.");
        } catch (IOException e) {
            System.out.println("Error writing to the User login CSV : " + e.getMessage());
        }

    }



    private User createUser(String hosID, String password, String role, String name, String gender, int age) {
        switch (role.toLowerCase()) {
            case "patient":
                return new Patient(hosID, password, role, name, gender, age);
            case "doctor":
                return new Doctor(hosID, password, role, name, gender, age);
            case "pharmacist":
                return new Pharmacist(hosID, password, role, name, gender, age);
            case "administrator":
                return new Administrator(hosID, password, role, name, gender, age);
            default:
                return null;
        }
    }
}
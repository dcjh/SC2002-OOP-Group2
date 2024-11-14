package App;
import java.util.Scanner;
import Authentication.AccountManager;
import Model.Shared.User;

public class App {

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        AccountManager auth = new AccountManager();

        while (option != 3) {
            System.out.print("Welcome to Hospital Management System\n");
            System.out.print("1. Login\n");
            System.out.print("2. Change Password\n");
            System.out.print("3. Exit\n");
            System.out.print("Select an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter your hospital ID: ");
                    String hosID = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    User user = auth.authenticate(hosID, password);
                    if (user != null) {
                        user.viewMenu();
                    } else {
                        System.out.println("Invalid credentials. Login is denied. Try again.");
                    }
                    break;

                case 2:
                    System.out.print("Enter your hospital ID: ");
                    String hosIDChangePassword = scanner.nextLine();
                    System.out.print("Enter your new password: ");
                    String newPassword = scanner.nextLine();

                    AccountManager.changePassword(hosIDChangePassword, newPassword);
                    System.out.println("Password changed successfully.");
                    break;

                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    break;
            }
        }
        scanner.close();
    }
}

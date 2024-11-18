package View.Pharmacist;

import java.util.Scanner;

import Controller.PharmacistController;
import Model.Shared.User;
import View.UserMainView;

public class PharmacistView implements UserMainView {
    private User user;
    private PharmacistController pharmacistController;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for PharmacistView, initializing the user and creating a new PharmacistController.
     *
     * @param user The user object representing the logged-in pharmacist.
     */
    public PharmacistView(User user) {
        this.user = user;
        this.pharmacistController = new PharmacistController();
    }

    /**
     * Constructor for PharmacistView, initializing with an existing PharmacistController.
     *
     * @param pharmacistController The pharmacist controller used to manage operations.
     */
    public PharmacistView(PharmacistController pharmacistController) {
        this.pharmacistController = pharmacistController;
    }

    /**
     * Displays the pharmacist menu to handle various operations, such as viewing and updating prescription status, managing inventory, and viewing appointment outcomes.
     *
     * @param hosID The hospital ID of the pharmacist.
     */
    public void menu(String hosID) {
        int option = 0;
        while (option != 6) {
            System.out.println();
            System.out.println("Pharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. View Replenishment Request");
            System.out.println("6. Logout");
            System.out.println();
            System.out.println("Select an option:");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    pharmacistController.displayAppointmentOutcome();
                    break;
                case 2:
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Key in the Patient ID to dispense medicine:");
                    String patientID = scanner1.nextLine();
                    pharmacistController.updatePrescriptionStatus(patientID);
                    break;
                case 3:
                    pharmacistController.displayInventory();
                    break;
                case 4:
                    pharmacistController.submitReplenishmentRequests();
                    break;
                case 5:
                    pharmacistController.viewAllReplenishmentRequests();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

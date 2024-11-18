package View.Patient;

import Model.Shared.User;
import View.UserMainView;
import Model.Roles.Patient;
import Controller.PatientController;
import java.util.Scanner;

public class PatientView implements UserMainView {
    private PatientController patientController;

    /**
     * Constructor for PatientView. Initializes the patient controller if the user is a valid patient.
     *
     * @param user The user to be represented in this view.
     */
    public PatientView(User user) {
        if (user instanceof Patient) {
            Patient patient = (Patient) user;
            this.patientController = new PatientController(patient);
        } else {
            System.out.println("Error: The user provided is not a Patient. Returning to the main menu.");
            this.patientController = null;
        }
    }

    /**
     * Displays the patient menu and handles user actions based on input.
     *
     * @param staffID The staff ID of the patient.
     */
    public void menu(String staffID) {
        int choice;
        do {
            System.out.println("\nPatient Menu:");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Contact Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. View Scheduled Appointments");
            System.out.println("5. Schedule Appointment");
            System.out.println("6. Reschedule Appointment");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. View Past Appointment Outcomes");
            System.out.println("9. Logout");

            choice = Integer.parseInt(prompt("Enter your choice: "));

            switch (choice) {
                case 1:
                    patientController.viewMedicalRecord();
                    break;
                case 2:
                    String newPhoneNumber = prompt("Enter new phone number: ");
                    String newEmail = prompt("Enter new email: ");
                    patientController.updateContactInformation(newPhoneNumber, newEmail);
                    break;
                case 3:
                    patientController.seeSchedule();
                    break;
                case 4:
                    patientController.viewScheduledAppointments();
                    break;
                case 5:
                    patientController.scheduleAppointment();
                    break;
                case 6:
                    patientController.rescheduleAppointment();
                    break;
                case 7:
                    patientController.cancelAppointment();
                    break;
                case 8:
                    patientController.viewPastAppointments();
                    break;
                case 9:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        } while (choice != 9);
    }

    /**
     * Helper method to prompt user input.
     *
     * @param message The message to display when prompting the user.
     * @return The user's input as a string.
     */
    private static String prompt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

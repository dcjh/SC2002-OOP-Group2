package View.Patient;

import Model.Shared.User;
import View.UserMainView;
import Model.Roles.Patient;
import Controller.PatientController;
import java.util.Scanner; 

import java.util.Scanner;

public class PatientView implements UserMainView{
    private PatientController patientController;

    public PatientView(User user) {
        if (user instanceof Patient) {
            Patient patient = (Patient) user;
            this.patientController = new PatientController(patient);
        } else {
            System.out.println("Error: The user provided is not a Patient. Returning to the main menu.");
            this.patientController = null;
        }
    }

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
                    //view avaiable slots
                    break;
                case 4:
                    patientController.viewScheduledAppointments();
                    break;
                case 5:
                    String docID = prompt("Enter Doctor ID: ");
                    String date = prompt("Enter Appointment Date (YYYY-MM-DD): ");
                    String time = prompt("Enter Appointment Time (HH:MM): ");
                    patientController.scheduleAppointment(docID, date, time);
                    break;
                case 6:
                    String rescheduleID = prompt("Enter Appointment ID to reschedule: ");
                    String newDate = prompt("Enter new date (YYYY-MM-DD): ");
                    String newTime = prompt("Enter new time (HH:MM): ");
                    patientController.rescheduleAppointment(rescheduleID, newDate, newTime);
                    break;
                case 7:
                    String cancelID = prompt("Enter Appointment ID to cancel: ");
                    patientController.cancelAppointment(cancelID);
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
        } while (choice != 8);
    }
    private static String prompt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}

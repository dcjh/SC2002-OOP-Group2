package MainPage;
import Model.Shared.User;
import Model.Roles.Patient;

import java.util.Scanner;

public class PatientMainPage {
    public static void main(String[] args) {
        User patient = getMainPage();
        MedicalRecord medicalRecord = dao.loadSingleRecord(patient.gethosID());//to a method that read patient
        PatientView patientView = new PatientView();
        PatientController patientController = new PatientController(medicalRecord, patientView);
        //appt?

        int choice;
        do {
            System.out.println("\nPatient Menu:");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Contact Information");
            System.out.println("3. View Scheduled Appointments");
            System.out.println("4. Schedule Appointment");//need work
            System.out.println("5. Reschedule Appointment"); //need work
            System.out.println("6. Cancel Appointment"); //need work
            System.out.println("7. View Past Appointment Outcomes");//need work
            System.out.println("8. Logout");

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
                    viewScheduledAppointments();
                    break;
                case 4:
                    // leave blank first
                    break;
                case 5:
                    //leave blank first
                    break;
                case 6:
                    int cancelIndex = Integer.parseInt(prompt("Enter appointment index to cancel: "));
                    cancelAppointment(cancelIndex);
                    break;
                case 7:
                    patientController.viewPastAppointment();
                    break;
                case 8:
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

package View.Doctor;

import Controller.DoctorController;
import java.util.Scanner;

public class DoctorView {
    private DoctorController doctorController;

    public DoctorView(DoctorController doctorController) {
        this.doctorController = doctorController;
    }

    public void menu(String doctorId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("Doctor Menu:");
            System.out.println("----------------------------------------------");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability for Appointments");
            System.out.println("5. Accept or Decline Appointment Requests");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome");
            System.out.println("8. Logout");
            System.out.println("----------------------------------------------");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    doctorController.viewPatientMR(doctorId);
                    break;
                case 2:
                    doctorController.updatePatientMR(doctorId);
                    break;
                case 3:
                    doctorController.viewDoctorSchedule(doctorId);
                    break;
                case 4:
                    doctorController.setAvailability(doctorId);
                    break;
                case 5:
                    doctorController.viewAppointmentRequests(doctorId);
                    break;
                case 6:
                    doctorController.viewUpcomingAppointments(doctorId);
                    break;
                case 7:
                    doctorController.viewRecordAppointmentOutcome(doctorId);
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public void displaySchedule(){
        
    }


}

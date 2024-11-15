package View.Doctor;

import Controller.DoctorController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DoctorView {
    private DoctorController doctorController;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DoctorView() {
        this.doctorController = new DoctorController();
    }

    public void menu(String doctorId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nDoctor Menu:");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability for Appointments");
            System.out.println("5. Accept or Decline Appointment Requests");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome");
            System.out.println("8. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    doctorController.viewPatientMR();
                    break;
                case 2:
                    doctorController.updatePatientMR();
                    break;
                case 3:
                    doctorController.viewDoctorSchedule(doctorId);
                    break;
                case 4:
                    doctorController.setAvailabilityView(doctorId);
                    break;
                case 5:
                    doctorController.appointmentRequestsView();
                    break;
                case 6:
                    doctorController.viewUpcomingAppointments();
                    break;
                case 7:
                    doctorController.recordAppointmentOutcome();
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }


}

package View;

import Controller.DoctorController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DoctorView {

    private DoctorController doctorController;
    private AvailabilityView availabilityView; // Add the AvailabilityView as a dependency
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DoctorView(DoctorController doctorController) {
        this.doctorController = doctorController;
        this.availabilityView = new AvailabilityView(doctorController); // Initialize AvailabilityView
    }

    public void showMenu(String doctorId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nDoctor Menu:");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. Manage Availability");
            System.out.println("4. Accept or Decline Appointment Requests");
            System.out.println("5. View Upcoming Appointments");
            System.out.println("6. Record Appointment Outcome");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    doctorController.viewPatientMedicalRecords();
                    break;
                case 2:
                    doctorController.updatePatientMedicalRecords();
                    break;
                case 3:
                    availabilityView.showAvailabilityMenu(doctorId); // Delegate to AvailabilityView
                    break;
                case 4:
                    doctorController.acceptOrDeclineAppointmentRequests();
                    break;
                case 5:
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);
                    doctorController.viewAvailableSlots(doctorId, date);
                    break;
                case 6:
                    doctorController.recordAppointmentOutcome();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

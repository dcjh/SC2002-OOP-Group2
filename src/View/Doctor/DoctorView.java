package View.Doctor;

import Controller.DoctorController;
import Model.Roles.Doctor;
import Model.Shared.User;
import View.UserMainView;
import java.util.Scanner;

/**
 * Represents the view for the Doctor user, providing a menu for various doctor-related actions.
 */
public class DoctorView implements UserMainView {

    private DoctorController doctorController;
    private Doctor doctor;

    /**
     * Constructor for DoctorView.
     * 
     * @param user The doctor user.
     */
    public DoctorView(User user) {
        this.doctor = (Doctor) user;
        this.doctorController = new DoctorController(doctor);
    }

    /**
     * Displays the doctor menu and allows the doctor to perform various actions.
     * 
     * @param doctorId The ID of the doctor.
     */
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
                    doctorController.viewPatientMR();
                    break;
                case 2:
                    doctorController.updatePatientMR();
                    break;
                case 3:
                    doctorController.viewDoctorSchedule();
                    break;
                case 4:
                    doctorController.setAvailability();
                    break;
                case 5:
                    doctorController.viewAppointmentRequests();
                    break;
                case 6:
                    doctorController.viewUpcomingAppointments();
                    break;
                case 7:
                    doctorController.viewRecordAppointmentOutcome();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}

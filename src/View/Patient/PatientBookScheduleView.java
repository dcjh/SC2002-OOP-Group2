package View.Patient;

import Controller.ScheduleController;
import Data.DataAccess.StaffDAO;
import Model.Shared.Schedule;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the view for booking a schedule for a patient.
 */
public class PatientBookScheduleView extends PatientScheduleView {

    
    private ScheduleController scheduleController;
    private StaffDAO staffDAO = new StaffDAO();

    /**
     * Constructor for PatientBookScheduleView.
     * 
     * @param scheduleController The controller for managing doctor schedules.
     */
    public PatientBookScheduleView(ScheduleController scheduleController) {
        super();
        this.scheduleController = scheduleController;
    }

    /**
     * Displays the menu for booking an appointment.
     * 
     * @param scheduleList A list of available schedules.
     * @param patientId The ID of the patient booking the appointment.
     */
    public void menu(List<Schedule> scheduleList, String patientId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----------------------------------------------");
            System.out.print("Would you like to book an appointment (y/n): ");
            String proceed = scanner.nextLine();
            switch (proceed) {
                case "y":
                    bookAppointment(scanner, patientId, scheduleList);
                    break;
                case "n":
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Proceeding...");
            }
        }
    }

    /**
     * Handles the appointment booking process.
     * 
     * @param scanner The scanner object for user input.
     * @param patientId The ID of the patient booking the appointment.
     * @param scheduleList A list of available schedules.
     */
    public void bookAppointment(Scanner scanner, String patientId, List<Schedule> scheduleList) {
        String br = "----------------------------------------------";
        boolean validInput = false;

        super.menu(scheduleList, staffDAO.findByRole("Doctor", null));
        System.out.println(br);

        while (!validInput) {
            System.out.println("Choose a doctor to book:");
            System.out.print("Enter Doctor ID: ");
            String doctorId = scanner.nextLine();
    
            System.out.print("Enter Date (dd-MM-yyyy): ");
            String date = scanner.nextLine();
    
            System.out.print("Enter Time (HH:mm): ");
            String time = scanner.nextLine();
    
            // Error checks
            boolean doctorExists = scheduleList.stream().anyMatch(schedule -> schedule.getDoctorId().equals(doctorId));

            if (!doctorExists) {
                System.out.println("Invalid Doctor ID. Please try again.");
                continue;
            }
    
            if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
                System.out.println("Invalid date format. Please enter the date in the format dd-MM-yyyy.");
                continue;
            }
    
            if (!time.matches("\\d{2}:\\d{2}")) {
                System.out.println("Invalid time format. Please enter the time in the format HH:mm.");
                continue;
            }
    
            // Check if the date and time are available for the doctor
            boolean dateAvailable = scheduleList.stream()
                .filter(schedule -> schedule.getDoctorId().equals(doctorId))
                .anyMatch(schedule -> schedule.getDateAvailability().containsKey(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                                      && schedule.getDateAvailability().get(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            if (!dateAvailable) {
                System.out.println("The selected date is not available. Please choose another date.");
                continue;
            }
    
            // If all checks pass, book the appointment
            validInput = true;
            scheduleController.createAppointmentRequest(doctorId, date, time);
            System.out.println("Appointment booked successfully for Doctor ID: " + doctorId + " on " + date + " at " + time);
        }
    }
}

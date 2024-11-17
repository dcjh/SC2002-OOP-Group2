package View.Doctor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import Controller.ScheduleController;

/**
 * The DoctorAvailabilityView class provides a user interface for doctors to manage their availability.
 */
public class DoctorAvailabilityView {

    private ScheduleController scheduleController; 
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Constructor for DoctorAvailabilityView.
     * 
     * @param scheduleController The controller used for managing doctor schedules.
     */
    public DoctorAvailabilityView(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    /**
     * Displays the menu for the doctor to view and set their availability.
     * 
     * @param doctorId The ID of the doctor.
     */
    public void menu(String doctorId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("Current Availability");
            scheduleController.showDoctorSchedule(doctorId);
            System.out.print("Edit Availability? (y/n): ");
            String proceed = scanner.nextLine();
            switch (proceed) {
                case "y":
                    setAvailability(scanner, doctorId);
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
     * Allows the doctor to set their availability for a specific date and time.
     * 
     * @param scanner The Scanner object for user input.
     * @param doctorId The ID of the doctor.
     */
    private void setAvailability(Scanner scanner, String doctorId) {
        LocalDate date = null;
        Boolean isAvailable = false;
        String time = null;

        System.out.print("Enter date (dd-MM-yyyy): ");
        while (true) {
            try {
                date = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        System.out.print("Enter time (HH:mm): ");
        while (true) {
            try {
                time = scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        System.out.print("Set as available? (true/false): ");
        while (true) {
            try {
                isAvailable = scanner.nextBoolean();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        scanner.nextLine(); // Consume newline
        scheduleController.updateDoctorSchedule(doctorId, date, isAvailable, time, true);
    }

}

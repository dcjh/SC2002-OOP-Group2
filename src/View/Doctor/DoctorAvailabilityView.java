package View.Doctor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import Controller.ScheduleController;


public class DoctorAvailabilityView {

    private ScheduleController scheduleController; 
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public DoctorAvailabilityView(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    public void menu(String doctorId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nCurrent Availability for for ");
            scheduleController.viewDoctorSchedule(doctorId);
            System.out.println("Edit Availability? (y/n)");
            String proceed = scanner.nextLine();
            switch (proceed) {
                case "y":
                    setAvailability(scanner,doctorId);
                    break;
                case "n":
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Proceeding...");
            }
            if (proceed.equals("n")) break;
        }
    }

    // Helper method to set availability for a specific date
    private void setAvailability(Scanner scanner, String doctorId) {
        LocalDate date = null;
        Boolean isAvailable = false;
        System.out.print("Enter date (dd-MM-yyyy): ");
        while (true) {
            try {
                date = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);
                break;
            } catch (Exception e) { System.out.println("Invalid input. Please try again."); }
        }
        System.out.print("Set as available? (true/false): ");
        while (true) {
            try {
                isAvailable = scanner.nextBoolean();
                break;
            } catch (Exception e) { System.out.println("Invalid input. Please try again."); }
        }
        
        scanner.nextLine(); // Consume newline
        scheduleController.updateDoctorSchedule(doctorId,date,isAvailable);
    }

}

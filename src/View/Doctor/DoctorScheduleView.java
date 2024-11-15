package View.Doctor;

import Controller.ScheduleController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class DoctorScheduleView {

    private ScheduleController controller;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DoctorScheduleView(ScheduleController controller) {
        this.controller = controller;
    }

    public void showAvailabilityMenu(String doctorId) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAvailability Menu:");
        System.out.println("1. View Personal Schedule");
        System.out.println("2. Set Availability for a Specific Date");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        
        do {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    doctorController.viewPersonalSchedule(doctorId);
                    break;
                case 2:
                    setAvailabilityForDate(scanner, doctorId);
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    // Helper method to set availability for a specific date
    private void setAvailabilityForDate(Scanner scanner, String doctorId) {
        System.out.print("Enter date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);
        System.out.println("Choose slot to set availability:");
        System.out.println("0 - 10:00 AM, 1 - 1:00 PM, 2 - 3:00 PM");
        System.out.print("Slot Index: ");
        int slotIndex = scanner.nextInt();
        System.out.print("Set as available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        doctorController.setAvailability(doctorId, date, slotIndex, isAvailable);
    }

    // Helper method to view available slots for a specific date
    private void viewAvailableSlotsForDate(Scanner scanner, String doctorId) {
        System.out.print("Enter date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);

        doctorController.viewAvailableSlots(doctorId, date);
    }
}


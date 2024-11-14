package View;

import Controller.DoctorController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AvailabilityView {

    private DoctorController doctorController;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AvailabilityView(DoctorController doctorController) {
        this.doctorController = doctorController;
    }

    public void showAvailabilityMenu(String doctorId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAvailability Menu:");
            System.out.println("1. View Personal Schedule");
            System.out.println("2. Set Availability for a Specific Date");
            System.out.println("3. View Available Slots for a Specific Date");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

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
                    viewAvailableSlotsForDate(scanner, doctorId);
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
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

        System.out.println("Available slots on " + date + ":");
        doctorController.viewAvailableSlots(doctorId, date);
    }
}


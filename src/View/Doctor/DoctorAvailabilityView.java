package View.Doctor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import Controller.ScheduleControllers.DSVController;

public class DoctorAvailabilityView {

    private DSVController DSVController;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DoctorAvailabilityView() {
        this.DSVController = new DSVController();
    }

    public void menu(String doctorId) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSetting Availability for for Doctor ID: " + doctorId);
        System.out.println("Continue? (y/n)");
        String proceed = scanner.nextLine();
        switch (proceed) {
            case "y":
                setAvailability(scanner,doctorId);
                break;
            case "n":
                System.out.println("Returning to Main Menu...");
                DSVController.returnToDoctorView(doctorId);;
                break;
            default:
                System.out.println("Invalid choice. Proceeding...");
        }
    }

    // Helper method to set availability for a specific date
    private void setAvailability(Scanner scanner, String doctorId) {
        LocalDate date = null;
        Boolean isAvailable = false;
        System.out.print("Enter date (yyyy-MM-dd): ");
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
        DSVController.updateDoctorSchedule(doctorId,date,isAvailable);
    }

}

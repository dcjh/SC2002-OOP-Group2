package View.Patient;

import Service.AvailabilityService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PatientScheduleView {
    private AvailabilityService availabilityService;
    private Scanner scanner;

    public PatientScheduleView(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
        this.scanner = new Scanner(System.in);
    }

    public void showDateSpecificAvailability() {
        System.out.print("Enter the date to view available slots for all doctors (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        // Retrieve availability for all doctors on the specified date
        Map<String, List<String>> availabilityByDoctor = availabilityService.getAllAvailableSlotsByDate(date);

        if (availabilityByDoctor.isEmpty()) {
            System.out.println("No available slots for any doctor on " + date + ".");
        } else {
            System.out.println("Available slots on " + date + ":");
            for (Map.Entry<String, List<String>> entry : availabilityByDoctor.entrySet()) {
                String doctorId = entry.getKey();
                List<String> availableSlots = entry.getValue();
                System.out.println("Doctor ID: " + doctorId + ", Slots: " + availableSlots);
            }
        }
    }
}


// package View;

// import Service.AvailabilityService;
// import Service.AppointmentService;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Map;
// import java.util.Scanner;

// public class PatientAvailabilityView {
//     private AvailabilityService availabilityService;
//     private AppointmentService appointmentService;
//     private Scanner scanner;

//     public PatientAvailabilityView(AvailabilityService availabilityService, AppointmentService appointmentService) {
//         this.availabilityService = availabilityService;
//         this.appointmentService = appointmentService;
//         this.scanner = new Scanner(System.in);
//     }

//     public void showDateSpecificAvailability() {
//         System.out.print("Enter the date to view available slots for all doctors (yyyy-MM-dd): ");
//         LocalDate date = LocalDate.parse(scanner.nextLine());

//         Map<String, List<String>> availabilityByDoctor = availabilityService.getAllAvailableSlotsByDate(date);

//         if (availabilityByDoctor.isEmpty()) {
//             System.out.println("No available slots for any doctor on " + date + ".");
//         } else {
//             System.out.println("Available slots on " + date + ":");
//             for (Map.Entry<String, List<String>> entry : availabilityByDoctor.entrySet()) {
//                 String doctorId = entry.getKey();
//                 List<String> availableSlots = entry.getValue();
//                 System.out.println("Doctor ID: " + doctorId + ", Slots: " + availableSlots);
//             }

//             // Offer booking option
//             System.out.print("Would you like to book an available slot? (yes/no): ");
//             String response = scanner.nextLine();
//             if (response.equalsIgnoreCase("yes")) {
//                 bookSlot(date);
//             }
//         }
//     }

//     private void bookSlot(LocalDate date) {
//         System.out.print("Enter the Doctor ID to book with: ");
//         String doctorId = scanner.nextLine();

//         // Display available slots for selected doctor and date
//         List<String> availableSlots = availabilityService.getAvailableSlots(doctorId, date);
//         if (availableSlots.isEmpty()) {
//             System.out.println("No available slots for Doctor " + doctorId + " on " + date + ".");
//             return;
//         }

//         System.out.println("Available slots for Doctor " + doctorId + " on " + date + ": " + availableSlots);
//         System.out.print("Choose a slot to book: ");
//         String slot = scanner.nextLine();

//         if (availableSlots.contains(slot)) {
//             boolean bookingSuccess = appointmentService.bookAppointment(doctorId, date, slot);
//             if (bookingSuccess) {
//                 System.out.println("Appointment booked successfully with Doctor " + doctorId + " on " + date + " at " + slot + ".");
//                 availabilityService.updateSlotAvailability(doctorId, date, availableSlots.indexOf(slot), false);
//             } else {
//                 System.out.println("Failed to book the appointment. Please try again.");
//             }
//         } else {
//             System.out.println("Invalid slot selection. Please select an available slot.");
//         }
//     }
// }

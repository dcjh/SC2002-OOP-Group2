package Controller;

import Service.DoctorService;
import java.time.LocalDate;
import java.util.List;

public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void viewPersonalSchedule(String doctorId) {
        doctorService.viewPersonalSchedule(doctorId);
    }

    public void setAvailability(String doctorId, LocalDate date, int slotIndex, boolean isAvailable) {
        boolean success = doctorService.setAvailability(doctorId, date, slotIndex, isAvailable);
        if (success) {
            System.out.println("Availability updated successfully.");
        } else {
            System.out.println("Failed to update availability.");
        }
    }

    public void viewAvailableSlots(String doctorId, LocalDate date) {
        List<String> availableSlots = doctorService.getAvailableSlots(doctorId, date);
        if (availableSlots.isEmpty()) {
            System.out.println("No slots available on " + date);
        } else {
            System.out.println("Available slots on " + date + ": " + availableSlots);
        }
    }

    // Placeholder methods for the upcoming features
    public void viewPatientMedicalRecords() {
        System.out.println("Feature in development...");
    }

    public void updatePatientMedicalRecords() {
        System.out.println("Feature in development...");
    }

    public void acceptOrDeclineAppointmentRequests() {
        System.out.println("Feature in development...");
    }

    public void recordAppointmentOutcome() {
        System.out.println("Feature in development...");
    }
}

package Service;

import Model.Shared.Availability;
import Service.AvailabilityService;

import java.time.LocalDate;
import java.util.List;

public class DoctorService {

    private AvailabilityService availabilityService;

    public DoctorService(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    public void viewPersonalSchedule(String doctorId) {
        List<Availability> schedule = availabilityService.getAvailabilityByDoctor(doctorId);
        System.out.println("Personal Schedule for Doctor " + doctorId + ":");
        for (Availability availability : schedule) {
            System.out.println(availability);
        }
    }

    public boolean setAvailability(String doctorId, LocalDate date, int slotIndex, boolean isAvailable) {
        return availabilityService.updateSlotAvailability(doctorId, date, slotIndex, isAvailable);
    }

    public List<String> getAvailableSlots(String doctorId, LocalDate date) {
        return availabilityService.getAvailableSlots(doctorId, date);
    }
}

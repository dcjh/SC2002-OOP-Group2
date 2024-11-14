package Service;

import Data.DataAccess.AvailabilityDAO;
import Model.Shared.Availability;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityService {

    private AvailabilityDAO availabilityDAO;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Constructor initializing the DAO
    public AvailabilityService() {
        this.availabilityDAO = new AvailabilityDAO();
    }

    // Retrieve all availability records (basic)
    public List<Availability> getAllAvailability() {
        return availabilityDAO.loadAll();
    }

    // Retrieve a specific availability record by doctorId and date (basic)
    public Availability getAvailableDate(String doctorId, LocalDate date) {
        return availabilityDAO.find(doctorId, date.format(DATE_FORMAT));
    }

    // Add or Update an availability record (basic)
    public void saveAvailability(Availability availability) {
        availabilityDAO.save(availability);
    }

    // Delete an availability record by doctorId and date
    public void deleteAvailability(String doctorId, LocalDate date) {
        availabilityDAO.delete(doctorId, date.format(DATE_FORMAT));
    }

    // Get available slots for a specific doctor on a given date
    public List<String> getAvailableSlots(String doctorId, LocalDate date) {
        Availability availability = getAvailableDate(doctorId, date);
        if (availability == null) {
            return new ArrayList<>();
        }
        List<String> availableSlots = new ArrayList<>();
        List<Boolean> slotAvailability = availability.getSlotAvailability();
        for (int i = 0; i < slotAvailability.size(); i++) {
            if (slotAvailability.get(i)) {
                availableSlots.add(Availability.SLOT_TIMES.get(i));
            }
        }
        return availableSlots;
    }

    // Update slot availability (book or make available) for a specific slot
    public boolean updateSlotAvailability(String doctorId, LocalDate date, int slotIndex, boolean isAvailable) {
        Availability availability = getAvailableDate(doctorId, date);
        if (availability != null && slotIndex >= 0 && slotIndex < availability.getSlotAvailability().size()) {
            List<Boolean> slots = availability.getSlotAvailability();
            if (slots.get(slotIndex) != isAvailable) {  // Only update if state differs
                slots.set(slotIndex, isAvailable);
                saveAvailability(availability);
                return true;
            }
        }
        return false;
    }

    // Helper method to check if all slots are booked on a given date
    public boolean isFullyBooked(String doctorId, LocalDate date) {
        Availability availability = getAvailableDate(doctorId, date);
        return availability != null && availability.isFullyBooked();
    }
}

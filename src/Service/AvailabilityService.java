package Service;

import Data.DataAccess.AvailabilityDAO;
import Model.Shared.Schedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AvailabilityService {

    private AvailabilityDAO availabilityDAO;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Constructor initializing the DAO
    public AvailabilityService() {
        this.availabilityDAO = new AvailabilityDAO();
    }

    // Retrieve all availability records (basic)
    public List<Schedule> getAllAvailability() {
        return availabilityDAO.loadAll();
    }

    // Retrieve a specific availability record by doctorId and date (basic)
    public Schedule getAvailableDate(String doctorId, LocalDate date) {
        return availabilityDAO.find(doctorId, date.format(DATE_FORMAT));
    }

    // Add or Update an availability record (basic)
    public void saveAvailability(Schedule availability) {
        availabilityDAO.save(availability);
    }

    // Delete an availability record by doctorId and date
    public void deleteAvailability(String doctorId, LocalDate date) {
        availabilityDAO.delete(doctorId, date.format(DATE_FORMAT));
    }

    // Method to load all availability records for a specific doctor ID
    public List<Schedule> loadAllByDoctorId(String doctorId) {
        List<Schedule> allAvailabilities = availabilityDAO.loadAll(); // Load all records
        return allAvailabilities.stream()
                .filter(a -> a.getDoctorId().equals(doctorId))
                .collect(Collectors.toList());
    }

    // Get available slots for a specific doctor on a given date
    public List<String> getAvailableSlots(String doctorId, LocalDate date) {
        Schedule availability = getAvailableDate(doctorId, date);
        if (availability == null) {
            return new ArrayList<>();
        }
        List<String> availableSlots = new ArrayList<>();
        List<Boolean> slotAvailability = availability.getSlotAvailability();
        for (int i = 0; i < slotAvailability.size(); i++) {
            if (slotAvailability.get(i)) {
                availableSlots.add(Schedule.SLOT_TIMES.get(i));
            }
        }
        return availableSlots;
    }

    public Map<String, List<String>> getAllAvailableSlotsByDate(LocalDate date) {
        List<Schedule> availabilities = availabilityDAO.loadAll();  // Load all records
        Map<String, List<String>> availabilityByDoctor = new HashMap<>();
    
        for (Schedule availability : availabilities) {
            if (availability.getDate().equals(date) && !availability.isFullyBooked()) {
                List<String> availableSlots = new ArrayList<>();
                List<Boolean> slotAvailability = availability.getSlotAvailability();
                
                for (int i = 0; i < slotAvailability.size(); i++) {
                    if (slotAvailability.get(i)) {
                        availableSlots.add(Schedule.SLOT_TIMES.get(i));
                    }
                }
                if (!availableSlots.isEmpty()) {
                    availabilityByDoctor.put(availability.getDoctorId(), availableSlots);
                }
            }
        }
        return availabilityByDoctor;
    }

    // Update slot availability (book or make available) for a specific slot
    public boolean updateSlotAvailability(String doctorId, LocalDate date, int slotIndex, boolean isAvailable) {
        Schedule availability = getAvailableDate(doctorId, date);
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
        Schedule availability = getAvailableDate(doctorId, date);
        return availability != null && availability.isFullyBooked();
    }
}

package Model.Shared;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Availability {
    private String doctorId;
    private LocalDate date;
    private String day;
    private List<Boolean> slotAvailability;

    // Constants for slot indices and times
    public static final int SLOT_10AM_INDEX = 0;
    public static final int SLOT_1PM_INDEX = 1;
    public static final int SLOT_3PM_INDEX = 2;
    public static final List<String> SLOT_TIMES = Arrays.asList("10:00 AM", "1:00 PM", "3:00 PM");

    // Constructor: Initializes all slots as available by default
    public Availability(String doctorId, LocalDate date, String day) {
        this.doctorId = doctorId;
        this.date = date;
        this.day = day;
        this.slotAvailability = new ArrayList<>(Arrays.asList(true, true, true)); // All slots available by default
    }

    // Constructor: Allows explicit setting of slot availability
    public Availability(String doctorId, LocalDate date, String day, List<Boolean> slotAvailability) {
        this.doctorId = doctorId;
        this.date = date;
        this.day = day;
        this.slotAvailability = new ArrayList<>(slotAvailability);
    }

    // Getters
    public String getDoctorId() {
        return doctorId;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getDay() {
        return day;
    }
    public List<Boolean> getSlotAvailability() {
        return new ArrayList<>(slotAvailability);
    }
    public void updateSlotAvailability(List<Boolean> slotAvailability) {
        this.slotAvailability = slotAvailability;
    }
    
    // Check if all slots are booked
    public boolean isFullyBooked() {
        return !slotAvailability.contains(true);
    }

    // Custom toString for debugging or logging
    @Override
    public String toString() {
        StringBuilder availabilityString = new StringBuilder("Date: " + date + ", Day: " + day + ", Doctor ID: " + doctorId + ", Slots: ");
        for (int i = 0; i < slotAvailability.size(); i++) {
            availabilityString.append(SLOT_TIMES.get(i)).append(": ")
                    .append(slotAvailability.get(i) ? "Available" : "Booked");
            if (i < slotAvailability.size() - 1) {
                availabilityString.append(", ");
            }
        }
        return availabilityString.toString();
    }
}

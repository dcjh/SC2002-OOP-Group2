package Model.Shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Availability {

    private String date;
    private boolean fullyBooked;
    private List<Boolean> slotAvailability;

    public static final int SLOT_10AM_INDEX = 0;
    public static final int SLOT_1PM_INDEX = 1;
    public static final int SLOT_3PM_INDEX = 2;
    public static final List<String> SLOT_TIMES = Arrays.asList("10:00 AM", "1:00 PM", "3:00 PM");

    //constructor
    public Availability(String date) {
        this.date = date;
        this.fullyBooked = false;
        this.slotAvailability = new ArrayList<>(Arrays.asList(true, true, true));
    }

    public Availability(String date, boolean fullyBooked, List<Boolean> slotAvailability) {
        this.date = date;
        this.fullyBooked = fullyBooked;
        this.slotAvailability = new ArrayList<>(slotAvailability);
    }

    //getter
    public String getDate() {
        return date;
    }
    public boolean isFullyBooked() {
        return fullyBooked;
    }
    public List<Boolean> getSlotAvailability() {
        return new ArrayList<>(slotAvailability);
    }
    public List<String> getAvailableSlots() {
        List<String> availableSlots = new ArrayList<>();
        for (int i = 0; i < slotAvailability.size(); i++) {
            if (slotAvailability.get(i)) {
                availableSlots.add(SLOT_TIMES.get(i));
            }
        }
        return availableSlots;
    }

    //slot booking
    public boolean bookSlot(int slotIndex) {
        if (fullyBooked) {
            System.out.println("The day is already fully booked.");
            return false;
        }
        if (slotIndex < 0 || slotIndex >= slotAvailability.size()) {
            System.out.println("Invalid slot index.");
            return false;
        }
        if (!slotAvailability.get(slotIndex)) {
            System.out.println("This slot is already booked.");
            return false;
        }
        // mark slot as booked
        slotAvailability.set(slotIndex, false);
        updateFullyBookedStatus();
        return true;
    }

    //setting availability
    public boolean makeSlotAvailable(int slotIndex) {
        if (slotIndex < 0 || slotIndex >= slotAvailability.size()) {
            System.out.println("Invalid slot index.");
            return false;
        }
        if (slotAvailability.get(slotIndex)) {
            System.out.println("This slot is already available.");
            return false;
        }

        // Mark the slot as available
        slotAvailability.set(slotIndex, true);
        updateFullyBookedStatus();
        return true;
    }

    // Check if a specific slot is available
    public boolean isSlotAvailable(int slotIndex) {
        if (slotIndex < 0 || slotIndex >= slotAvailability.size()) {
            System.out.println("Invalid slot index.");
            return false;
        }
        return slotAvailability.get(slotIndex);
    }

    // Private helper to update fullyBooked status based on slot availability
    private void updateFullyBookedStatus() {
        fullyBooked = !slotAvailability.contains(true); // Set fullyBooked if no slots are available
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Fully Booked: " + fullyBooked + ", Slot Availability: " + slotAvailability;
    }
    
}

package Repository.DAO;

import Repository.DataClasses.Availability;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityDAO implements DataAccessObject<Availability, String>{
    
    private static final String FILE_PATH = "src/DataFiles/Availability.csv";

    // Load all availability records for a specific doctor
    @Override
    public List<Availability> loadAll(String doctorID) {
        List<Availability> availabilityList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(doctorID)) {  // Filter by doctor ID
                    String date = values[1];
                    List<Boolean> slotAvailability = new ArrayList<>();
                    slotAvailability.add(Boolean.parseBoolean(values[2])); // 10AM slot
                    slotAvailability.add(Boolean.parseBoolean(values[3])); // 1PM slot
                    slotAvailability.add(Boolean.parseBoolean(values[4])); // 3PM slot
                    boolean fullyBooked = Boolean.parseBoolean(values[5]);

                    // Create Availability object
                    Availability availability = new Availability(date, fullyBooked, slotAvailability);
                    availabilityList.add(availability);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading availability dates: " + e.getMessage());
        }
        return availabilityList;
    }

    // Save all availability records for a specific doctor to the CSV file
    @Override
    public void saveAll(List<Availability> availabilityList, String doctorID) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) { // overwrite mode
            for (Availability availability : availabilityList) {
                writer.write(formatAvailabilityCSVLine(availability, doctorID));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving availability dates: " + e.getMessage());
        }
    }

    // Find specific availability by doctor ID and date
    @Override
    public Availability find(String doctorID, String date) {
        List<Availability> availabilityList = loadAll(doctorID);
        for (Availability availability : availabilityList) {
            if (availability.getDate().equals(date)) {
                return availability;
            }
        }
        return null;
    }

    // Delete a specific availability record for a given doctor
    @Override
    public void delete(Availability item, String doctorID) {
        List<Availability> availabilityList = loadAll(doctorID);
        availabilityList.removeIf(availability -> availability.getDate().equals(item.getDate()));
        saveAll(availabilityList, doctorID);
    }

    // Helper method to format an Availability object as a CSV line
    private String formatAvailabilityCSVLine(Availability availability, String doctorID) {
        List<Boolean> slots = availability.getSlotAvailability();
        return String.join(",",
                doctorID,
                availability.getDate(),
                String.valueOf(slots.get(0)),   // 10:00 AM
                String.valueOf(slots.get(1)),   // 1:00 PM
                String.valueOf(slots.get(2)),   // 3:00 PM
                String.valueOf(availability.isFullyBooked())
        );
    }

}

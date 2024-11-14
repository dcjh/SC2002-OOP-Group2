package Data.DataAccess;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Model.Shared.Availability;

public class AvailabilityDAO implements DataAccessObject<Availability, String> {

    private static final String FILE_PATH = "src/DataFiles/Availability.csv";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Load all availability records
    public List<Availability> loadAll() {
        List<Availability> availabilityList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String doctorId = values[0];
                LocalDate date = LocalDate.parse(values[1], DATE_FORMAT);  // Use DATE_FORMAT to parse
                String day = values[2];

                // Parse slot availability
                List<Boolean> slotAvailability = new ArrayList<>();
                slotAvailability.add(Boolean.parseBoolean(values[3])); // 10AM slot
                slotAvailability.add(Boolean.parseBoolean(values[4])); // 1PM slot
                slotAvailability.add(Boolean.parseBoolean(values[5])); // 3PM slot

                // Create and add Availability object
                Availability availability = new Availability(doctorId, date, day, slotAvailability);
                availabilityList.add(availability);
            }
        } catch (IOException e) {
            System.err.println("Error loading availability data: " + e.getMessage());
        }
        return availabilityList;
    }

    // Create or update an availability record
    public void save(Availability availability) {
        List<Availability> allRecords = loadAll();  // Load all records to modify in place
        // Remove any existing record with the same doctor ID and date
        allRecords.removeIf(existing -> existing.getDoctorId().equals(availability.getDoctorId()) &&
                                        existing.getDate().equals(availability.getDate()));
        allRecords.add(availability);  // Add the new or updated record
        writeAll(allRecords);          // Write all records back to the CSV
    }

    // Find an availability record by doctor ID and date
    public Availability find(String doctorId, String date) {
        List<Availability> availabilityList = loadAll();
        for (Availability availability : availabilityList) {
            if (availability.getDoctorId().equals(doctorId) &&
                availability.getDate().format(DATE_FORMAT).equals(date)) {  // Use DATE_FORMAT for comparison
                return availability;
            }
        }
        return null;
    }

    // Delete a specific availability record by doctor ID and date
    public void delete(String doctorId, String date) {
        List<Availability> allRecords = loadAll();
        allRecords.removeIf(availability -> availability.getDoctorId().equals(doctorId) &&
                                             availability.getDate().format(DATE_FORMAT).equals(date));
        writeAll(allRecords);  // Write the updated list back to the CSV
    }

    // Private helper method to write all availability records to the CSV
    private void writeAll(List<Availability> availabilityList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {  // overwrite mode
            for (Availability availability : availabilityList) {
                writer.write(formatAvailabilityCSVLine(availability));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving availability data: " + e.getMessage());
        }
    }

    // Helper method to format an Availability object as a CSV line
    private String formatAvailabilityCSVLine(Availability availability) {
        List<Boolean> slots = availability.getSlotAvailability();
        return String.join(",",
                availability.getDoctorId(),
                availability.getDate().format(DATE_FORMAT),  // Use DATE_FORMAT to format
                availability.getDay(),
                String.valueOf(slots.get(0)),  // 10:00 AM
                String.valueOf(slots.get(1)),  // 1:00 PM
                String.valueOf(slots.get(2))   // 3:00 PM
        );
    }
}

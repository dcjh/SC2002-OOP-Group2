package Data.DataAccess;

import java.io.*;
import java.util.*;

/**
 * The StaffDAO class handles CRUD operations for staff data.
 * This class manages reading, writing, and updating staff records in a CSV file.
 */
public class StaffDAO {
    private static final String FILE_PATH = "src/Data/Assets/Staff_List.csv";

    /**
     * Loads all staff records from the CSV file.
     * 
     * @return A list of maps representing all staff records.
     */
    public List<Map<String, String>> loadAll() {
        List<Map<String, String>> staffList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine(); 
            if (line == null) {
                System.out.println("The file is empty.");
                return staffList;
            }

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != 5) {
                    System.err.println("Skipping malformed row: " + line);
                    continue;
                }

                Map<String, String> staff = new HashMap<>();
                staff.put("staffID", values[0].trim());
                staff.put("name", values[1].trim());
                staff.put("role", values[2].trim());
                staff.put("gender", values[3].trim());
                staff.put("age", values[4].trim());
                staffList.add(staff);
            }
        } catch (IOException e) {
            System.err.println("Error reading staff list: " + e.getMessage());
        }
        return staffList;
    }

    /**
     * Saves or updates a staff record in the CSV file.
     * 
     * @param staff A map representing the staff record to be saved.
     */
    public void save(Map<String, String> staff) {
        List<Map<String, String>> staffList = loadAll();
        boolean exists = false;

        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).get("staffID").equals(staff.get("staffID"))) {
                staffList.set(i, staff); 
                exists = true;
                break;
            }
        }

        if (!exists) {
            staffList.add(staff);
        }

        saveAll(staffList);
    }

    /**
     * Finds a staff record by its ID.
     * 
     * @param staffId The ID of the staff member to find.
     * @param searchKey A search key (not used in the current implementation).
     * @return A map representing the found staff record, or null if not found.
     */
    public Map<String, String> find(String staffId, String searchKey) {
        List<Map<String, String>> staffList = loadAll();
        for (Map<String, String> staff : staffList) {
            if (staff.get("staffID").equals(staffId)) {
                return staff;
            }
        }
        return null;
    }


    /**
     * Finds and returns a list of staff members based on their role.
     *
     * @param role      The role of the staff members to be searched (e.g., "Doctor", "Nurse").
     * @param searchKey An additional search key to be used in the future (currently unused).
     * @return A list of staff members who match the specified role.
     *         Each staff member is represented as a map of key-value pairs.
     *
     * This method filters the list of all staff members to return only those whose role matches
     * the specified role (case-insensitive).
     */
    public List<Map<String, String>> findByRole(String role, String searchKey) {
        List<Map<String, String>> staffList = loadAll();
        List<Map<String, String>> roleList = new ArrayList<>();
        for (Map<String, String> staff : staffList) {
            if (staff.get("role").equalsIgnoreCase(role)) {
                roleList.add(staff);
            }
        }
        return roleList;
    }


    /**
     * Deletes a staff record by its ID.
     * 
     * @param staffId The ID of the staff member to delete.
     * @param searchKey A search key (not used in the current implementation).
     */
    public void delete(String staffId, String searchKey) {
        List<Map<String, String>> staffList = loadAll();
        staffList.removeIf(staff -> staff.get("staffID").equals(staffId));
        saveAll(staffList);
    }

    /**
     * Saves all staff records to the CSV file.
     * 
     * @param staffList A list of maps representing the staff records to be saved.
     */
    private void saveAll(List<Map<String, String>> staffList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("Staff ID,Name,Role,Gender,Age");
            writer.newLine();
            for (Map<String, String> staff : staffList) {
                writer.write(formatStaffCSVLine(staff));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving staff data: " + e.getMessage());
        }
    }

    /**
     * Formats a staff record as a CSV line.
     * 
     * @param staff A map representing the staff record.
     * @return A string formatted as a CSV line.
     */
    private String formatStaffCSVLine(Map<String, String> staff) {
        return String.join(",",
                staff.get("staffID"),
                staff.get("name"),
                staff.get("role"),
                staff.get("gender"),
                staff.get("age")
        );
    }
}

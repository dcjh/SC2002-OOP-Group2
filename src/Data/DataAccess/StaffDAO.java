package Data.DataAccess;

import java.io.*;
import java.util.*;

public class StaffDAO {
    private static final String FILE_PATH = "src/Data/Assets/Staff_List.csv";

    
    /** 
     * @return List<Map<String, String>>
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
     * @param staff
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

    public Map<String, String> find(String staffId, String searchKey) {
        List<Map<String, String>> staffList = loadAll();
        for (Map<String, String> staff : staffList) {
            if (staff.get("staffID").equals(staffId)) {
                return staff;
            }
        }
        return null;
    }

    public void delete(String staffId, String searchKey) {
        List<Map<String, String>> staffList = loadAll();
        staffList.removeIf(staff -> staff.get("staffID").equals(staffId));
        saveAll(staffList);
    }

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

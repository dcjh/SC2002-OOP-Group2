package Data.DataAccess;

import java.io.*;
import java.util.*;

/**
 * The UserDAO class handles CRUD operations for user data.
 * This class manages reading, writing, and updating user records in a CSV file.
 */
public class UserDAO {
    private static final String FILE_PATH = "src/Data/Assets/userlogin.csv";

    /**
     * Loads all user records from the CSV file.
     * 
     * @return A list of maps representing all user records.
     */
    public List<Map<String, String>> loadAll() {
        List<Map<String, String>> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine(); // Skip the header line
            if (line == null) {
                System.out.println("The file is empty.");
                return userList;
            }

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 7) {
                    System.err.println("Skipping malformed row: " + line);
                    continue;
                }

                Map<String, String> user = new HashMap<>();
                user.put("hosID", values[0].trim());
                user.put("password", values[1].trim());
                user.put("role", values[2].trim());
                user.put("name", values[3].trim());
                user.put("gender", values[4].trim());
                user.put("age", values[5].trim());
                user.put("initialLogin", values[6].trim());
                userList.add(user);
            }
        } catch (IOException e) {
            System.err.println("Error reading user list: " + e.getMessage());
        }
        return userList;
    }

    /**
     * Saves or updates a user record in the CSV file.
     * 
     * @param user A map representing the user record to be saved.
     */
    public void save(Map<String, String> user) {
        List<Map<String, String>> userList = loadAll();
        boolean exists = false;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).get("hosID").equals(user.get("hosID"))) {
                userList.set(i, user); 
                exists = true;
                break;
            }
        }

        if (!exists) {
            userList.add(user);
        }

        saveAll(userList);
    }

    /**
     * Finds a user record by hosID.
     * 
     * @param hosID The ID of the user to find.
     * @param searchKey A search key (not used in the current implementation).
     * @return A map representing the found user record, or null if not found.
     */
    public Map<String, String> find(String hosID, String searchKey) {
        List<Map<String, String>> userList = loadAll();
        for (Map<String, String> user : userList) {
            if (user.get("hosID").equals(hosID)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Deletes a user record by hosID.
     * 
     * @param hosID The ID of the user to delete.
     * @param searchKey A search key (not used in the current implementation).
     */
    public void delete(String hosID, String searchKey) {
        List<Map<String, String>> userList = loadAll();
        userList.removeIf(user -> user.get("hosID").equals(hosID));
        saveAll(userList);
    }

    /**
     * Saves all user records to the CSV file.
     * 
     * @param userList A list of maps representing the user records to be saved.
     */
    private void saveAll(List<Map<String, String>> userList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("hosID,password,role,name,gender,age,initialLogin");
            writer.newLine();
            for (Map<String, String> user : userList) {
                writer.write(formatUserCSVLine(user));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving user data: " + e.getMessage());
        }
    }

    /**
     * Formats a user record as a CSV line.
     * 
     * @param user A map representing the user record.
     * @return A string formatted as a CSV line.
     */
    private String formatUserCSVLine(Map<String, String> user) {
        return String.join(",",
                user.get("hosID"),
                user.get("password"),
                user.get("role"),
                user.get("name"),
                user.get("gender"),
                user.get("age"),
                user.get("initialLogin")
        );
    }
}

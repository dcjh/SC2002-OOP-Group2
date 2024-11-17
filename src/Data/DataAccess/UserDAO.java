package Data.DataAccess;

import java.io.*;
import java.util.*;

public class UserDAO {
    private static final String FILE_PATH = "C:\\Users\\chuaz\\eclipse-workspace\\OOP_project\\src\\Authentication\\userlogin.csv";

    
    
    /** 
     * @return List<Map<String, String>>
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
     * @param user
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


    public Map<String, String> find(String hosID, String searchKey) {
        List<Map<String, String>> userList = loadAll();
        for (Map<String, String> user : userList) {
            if (user.get("hosID").equals(hosID)) {
                return user;
            }
        }
        return null;
    }


    public void delete(String hosID, String searchKey) {
        List<Map<String, String>> userList = loadAll();
        userList.removeIf(user -> user.get("hosID").equals(hosID));
        saveAll(userList);
    }

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

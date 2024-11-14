package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
	    private static final String FILE_PATH = "SC2002-OOP-Group2\\src\\Data\\Assets\\userlogin.csv";

	    public List<Map<String, String>> load() {
	        List<Map<String, String>> userDatabase = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
	            String line = reader.readLine(); 
	            while ((line = reader.readLine()) != null) {
	                String[] values = line.split(",");
	                
	                if (values.length < 5) {
	                    System.out.println("Malformed line in staff list, skipping: " + line);
	                    continue;
	                }
	                
	                Map<String, String> userMap = new HashMap<>();
	                userMap.put("hosID", values[0].trim());
	                userMap.put("password", values[1].trim());
	                userMap.put("role", values[2].trim());
	                userMap.put("name", values[3].trim());
	                userMap.put("initialLogin", values[4].trim());
	                userDatabase.add(userMap);
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading user list: " + e.getMessage());
	        }
	        return userDatabase;
	    }
			    
		    
		 public void write(List<Map<String, String>> userDatabase) {
		        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
		        	writer.write("hosID,password,role,name,initialLogin");
		        	writer.newLine();
		        	for (Map<String, String> user : userDatabase) {
		                writer.write(String.join(",", 
		                        user.get("hosID"),
		                        user.get("password"),
		                        user.get("role"),
		                        user.get("name"),
		                        user.get("initialLogin")));
		                writer.newLine();
		            }
		        } catch (IOException e) {
		            System.out.println("Error writing to the file: " + e.getMessage());
		        }
		    }
			 
		 
		 public void append(String[] data) {
		        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
		            writer.write(String.join(",", data));
		            writer.newLine();
		        } catch (IOException e) {
		            System.out.println("Error appending to the file: " + e.getMessage());
		        }
		 	}
		}

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

public class PatientRepository {
    private static final String FILE_PATH = "SC2002-OOP-Group2\src\Data\Repository\Patient_List.csv";

    public List<Map<String, String>> load() {
        List<Map<String, String>> patientDatabase = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                
                if (values.length < 6) {
                    System.out.println("Malformed line in staff list, skipping: " + line);
                    continue;
                }
                
                Map<String, String> patientMap = new HashMap<>();
                patientMap.put("Patient ID", values[0].trim());
                patientMap.put("Name", values[1].trim());
                patientMap.put("Date of Birth", values[2].trim());
                patientMap.put("Gender", values[3].trim());
                patientMap.put("Blood Type", values[4].trim());
                patientMap.put("Contact Information", values[5].trim());
                patientDatabase.add(patientMap);
            }
        } catch (IOException e) {
            System.out.println("Error reading staff list: " + e.getMessage());
        }
        return patientDatabase;
    }
		    
	    
	 public void write(List<Map<String, String>> patientDatabase) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
	        	writer.write("Patient ID,Name,Date of Birth,Gender,Blood Type,Contact Information");
	        	writer.newLine();
	        	for (Map<String, String> patient : patientDatabase) {
	                writer.write(String.join(",", 
	                        patient.get("Patient ID"),
	                        patient.get("Name"),
	                        patient.get("Date of Birth"),
	                        patient.get("Gender"),
	                        patient.get("Blood Type"),
	                        patient.get("Contact Information")));
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

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

public class MedicineRepository {
	   private static final String FILE_PATH = "SC2002-OOP-Group2\src\Data\Assets\Medicine_List.csv";
	    public List<Map<String, String>> load() {
	        List<Map<String, String>> medicineDatabase = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
	            String line = reader.readLine(); 
	            while ((line = reader.readLine()) != null) {
	                String[] values = line.split(",");
	                
	                if (values.length < 3) {
	                    continue;
	                }
	                
	                Map<String, String> medicineMap = new HashMap<>();
	                medicineMap.put("Medicine Name", values[0].trim());
	                medicineMap.put("Initial Stock", values[1].trim());
	                medicineMap.put("Low Stock Level Alert", values[2].trim());
	                medicineDatabase.add(medicineMap);
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading medicine list: " + e.getMessage());
	        }
	        return medicineDatabase;
	    }
			    
		    
		 public void write(List<Map<String, String>> medicineDatabase) {
		        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
		        	writer.write("Medicine Name,Initial Stock,Low Stock Level Alert");
		        	writer.newLine();
		        	for (Map<String, String> medicine : medicineDatabase) {
		                writer.write(String.join(",", 
		                		medicine.get("Medicine Name"),
		                		medicine.get("Initial Stock"),
		                		medicine.get("Low Stock Level Alert")));
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

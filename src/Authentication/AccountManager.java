package Authentication;

import Model.Roles.Gender;
import Model.Roles.UserType;
import Model.Shared.User;
import Model.UserFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountManager {
	private static final String FILE_PATH = "src/Data/Assets/userlogin.csv";

	public User login(String hospID, String password) {
		List<String[]> userDatabase = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
	    	String header = reader.readLine();
	    	userDatabase.add(header.split(","));
            String line;
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String userHosID = values[0].trim();
                String userPassword = values[1].trim();
                UserType userRole = UserType.valueOf(values[2].trim().toUpperCase());
                String userName = values[3].trim();
                String userGender = values[4].trim();

                int userAge = Integer.parseInt(values[5].trim());
                String initialLogin = values[6].trim();
                
                if (userHosID.equals(hospID) && userPassword.equals(password)) {
                    System.out.println("Login successful for user, Welcome back " + userName);
                    
                    if (initialLogin.equals("TRUE")) {
                    	System.out.println("Please change your password, as we notice that this is your first login");
                    	Scanner scanner = new Scanner(System.in);
                    	System.out.print("Enter your new password: ");
                    	String newPassword = scanner.nextLine();
                    	changePassword(hospID,newPassword);
                    }
                    return UserFactory.createUser(userRole, hospID, password, userName, Gender.valueOf(userGender.toUpperCase()), userAge);
                } 
                
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV database:" + e.getMessage());
        }      
        return null;
    }

	
	   public static void changePassword(String hospID, String newPassword) {
	    	List<String[]> fileContent = new ArrayList<>();
	        	try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
	            	String header = reader.readLine();
	            	fileContent.add(header.split(","));
	            	String line;
	                while ((line = reader.readLine()) != null) {
	                    String[] values = line.split(",");
	                    
	                    if (values[0].trim().equalsIgnoreCase(hospID)) {
	                    	 values[1] = newPassword;  
	                    }
	                    
	                    if (values[0].trim().equalsIgnoreCase(hospID) && values[6].trim().equalsIgnoreCase("TRUE")) {
	                   	 values[6] = "FALSE";
	                   }
	                    fileContent.add(values); 
	                }


	        	}
	        	catch (IOException e) {
	                System.out.println("Error reading the CSV file: " + e.getMessage());
	                return;
	            }  


	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
	                for (String[] values : fileContent) {
	                    writer.write(String.join(",", values));
	                    writer.newLine();
	                }
	                System.out.println("Password successfully changed.");
	            } catch (IOException e) {
	                System.out.println("Error writing to the User login CSV : " + e.getMessage());
	            }	
	
	   }

}

package Controller;

import java.io.*;
import java.util.*;
import Model.Gender;
import Model.UserType;
import Data.Repository.StaffRepository;
import Data.Repository.UserRepository;
import Data.Repository.MedicalRepository;
import Data.Repository.PatientRepository;

public class AdministratorController {
	public static void addUserMember() {
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter staff ID:\n ");    	
	    String newstaffID = scanner.nextLine();
	    System.out.print("Enter name:\n");
	    String newstaffName = scanner.nextLine();
	    UserType newStaffRole = null;
        while (newStaffRole == null) {
            System.out.println("Select role:");
            for (UserType type : UserType.values()) {
                System.out.println(type.ordinal() + 1 + ". " + type);
            }
            System.out.print("Enter choice (1-" + UserType.values().length + "): ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine();

            if (roleChoice >= 1 && roleChoice <= UserType.values().length) {
                newStaffRole = UserType.values()[roleChoice - 1];
            } else {
                System.out.println("Invalid choice. Please select a valid role.");
            }
        }
	    
	    
        Gender newStaffGender = null;
        while (newStaffGender == null) {
            System.out.println("Select gender:");
            for (Gender gender : Gender.values()) {
                System.out.println(gender.ordinal() + 1 + ". " + gender);
            }
            System.out.print("Enter choice (1-" + Gender.values().length + "): ");
            int genderChoice = scanner.nextInt();
            scanner.nextLine();

            if (genderChoice >= 1 && genderChoice <= Gender.values().length) {
                newStaffGender = Gender.values()[genderChoice - 1];
            } else {
                System.out.println("Invalid choice. Please select a valid gender.");
            }
        }
        
        String dob = null; 
        String phoneNumber = null;
        String allergies = null;
        String bloodType = null;
        String email = null;
       
        if (newStaffRole == UserType.PATIENT) {
        	System.out.println("Enter Date of Birth (YYYY-MM-DD)");
        	dob = scanner.nextLine();
        	
        	System.out.println("Enter Phone Number:");
        	phoneNumber = scanner.nextLine();
        	
        	System.out.println("Enter Allergies:");
        	allergies = scanner.nextLine(); 
        	
        	System.out.println("Enter Blood Type:");
        	bloodType = scanner.nextLine(); 
        	
        	System.out.println("Enter your email address:");
        	email = scanner.nextLine(); 
        }
        
        
        
	    System.out.print("Enter age: \n");
	    int newstaffage = scanner.nextInt();
	    String defaultPassword = "password123";
	    String initialLogin = "TRUE";
	    
	    if(newStaffRole == UserType.PATIENT) {
	    	String[] newPatientData = { newstaffID, newstaffName, dob,newStaffGender.toString(),bloodType, allergies, phoneNumber, email };
		    PatientRepository patientRepository = new PatientRepository();
			patientRepository.append(newPatientData);

	    }else {
	    	String[] newStaffData = {newstaffID, newstaffName, newStaffRole.toString(),  newStaffGender.toString(), String.valueOf(newstaffage)};
		    StaffRepository staffRepository = new StaffRepository();
			staffRepository.append(newStaffData);
	    }
	  
	    String[] newUserData = { newstaffID, defaultPassword, newStaffRole.toString(), newstaffName,newStaffGender.toString(), String.valueOf(newstaffage), initialLogin };
	    UserRepository userRepository = new UserRepository();
		userRepository.append(newUserData);
	    System.out.println("Staff/User member added successfully.");
	    	} 
	
	   public static void uodateUserMember() {
		   Scanner scanner = new Scanner(System.in);
	       StaffRepository staffRepository = new StaffRepository();
	       List<Map<String, String>> staffDatabase = staffRepository.load();

	        System.out.print("Enter staff ID to update: ");
	        String staffID = scanner.nextLine();
	        Map<String, String> staff = staffDatabase.stream()
	                .filter(s -> s.get("staffID").equals(staffID))
	                .findFirst()
	                .orElse(null);

	        if (staff == null) {
	            System.out.println("Staff member with ID " + staffID + " not found.");
	            return;
	        }

	        boolean updating = true;
	        while (updating) {
	            System.out.println("Select the detail to update:");
	            System.out.println("1. Name");
	            System.out.println("2. Role");
	            System.out.println("3. Gender");
	            System.out.println("4. Age");
	            System.out.println("5. Done");
	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter new name: ");
	                    staff.put("name", scanner.nextLine());
	                    break;

	                case 2:
	                    System.out.println("Select new role:");
	                    for (UserType type : UserType.values()) {
	                        System.out.println((type.ordinal() + 1) + ". " + type);
	                    }
	                    System.out.print("Enter choice (1-" + UserType.values().length + "): ");
	                    int roleChoice = scanner.nextInt();
	                    scanner.nextLine();

	                    if (roleChoice >= 1 && roleChoice <= UserType.values().length) {
	                        UserType newRole = UserType.values()[roleChoice - 1];
	                        staff.put("role", newRole.name());
	                    } else {
	                        System.out.println("Invalid role choice.");
	                    }
	                    break;

	                case 3:
	                    System.out.println("Select new gender:");
	                    for (Gender gender : Gender.values()) {
	                        System.out.println((gender.ordinal() + 1) + ". " + gender);
	                    }
	                    System.out.print("Enter choice (1-" + Gender.values().length + "): ");
	                    int genderChoice = scanner.nextInt();
	                    scanner.nextLine();

	                    if (genderChoice >= 1 && genderChoice <= Gender.values().length) {
	                        Gender newGender = Gender.values()[genderChoice - 1];
	                        staff.put("gender", newGender.name());
	                    } else {
	                        System.out.println("Invalid gender choice.");
	                    }
	                    break;

	                case 4:
	                    System.out.print("Enter new age: ");
	                    staff.put("age", scanner.nextLine());
	                    break;

	                case 5:
	                    updating = false;
	                    break;

	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }

	        staffRepository.write(staffDatabase);
	        System.out.println("Staff member updated successfully.");
	    }
	  

	   public static void removeUserMember() {
	        Scanner scanner = new Scanner(System.in);
	        StaffRepository staffRepository = new StaffRepository();
	        List<Map<String, String>> staffDatabase = staffRepository.load();
	        UserRepository userRepository = new UserRepository();
	        List<Map<String, String>> userDatabase = userRepository.load();
	        PatientRepository patientRepository = new PatientRepository();
	        List<Map<String, String>> patientDatabase = patientRepository.load();
	        
	        
	        System.out.print("Enter staff ID to remove: ");
	        String staffID = scanner.nextLine();
	        boolean removedStaff = staffDatabase.removeIf(staff -> staff.get("staffID").equals(staffID));
	        boolean removedUser = userDatabase.removeIf(user -> user.get("hosID").equals(staffID));
	        boolean removedPatient = patientDatabase.removeIf(patient -> patient.get("Patient ID").equals(staffID));
	        if (removedStaff) {
	        	staffRepository.write(staffDatabase);
	        } 
	        if (removedUser) {
	        	userRepository.write(userDatabase);
	        } 
	        
	        if (removedPatient) {
	        	userRepository.write(patientDatabase);
	        	System.out.println("User member with ID " + staffID + " removed successfully.");
	        } 
	        
	        
	        else {
	            System.out.println("User member with ID " + staffID + " not found.");
	        }
	              
	   } 
	   
	   public static void addMed() {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Medicine Name:\n ");    	
			String newMedicinename = scanner.nextLine();   
	        System.out.print("Enter Initial Stock: \n");
	        int newMedicineinitialstock = scanner.nextInt();
	        double newMedicinelowstocklevel = 0.2 * newMedicineinitialstock;
	        String[] newMedData = {newMedicinename, String.valueOf(newMedicineinitialstock), String.valueOf(newMedicinelowstocklevel)};
		    MedicalRepository medicalRepository = new MedicalRepository();
		    medicalRepository.append(newMedData);
		    System.out.println("New medicine added successfully.");
	   }
	   
	   public static void removeMed() {
	        Scanner scanner = new Scanner(System.in);
	        MedicalRepository medicalRepository = new MedicalRepository();
	        
	        List<Map<String, String>> medicalDatabase = medicalRepository.load();
	        
	        System.out.print("Enter medication name to remove: ");
	        String medicationName = scanner.nextLine();

	        boolean removed = medicalDatabase.removeIf(medicine -> medicine.get("Medicine Name").equalsIgnoreCase(medicationName));
	        
	        if (removed) {
	            medicalRepository.write(medicalDatabase);
	            System.out.println("Medicine " + medicationName + " removed successfully.");
	        } else {
	            System.out.println("Medicine " + medicationName + " not found.");
	        }
	    }   
	   
	   public static void updateStocklevel() {
		   	Scanner scanner = new Scanner(System.in);
	        MedicalRepository medicalRepository = new MedicalRepository();
	        
	        List<Map<String, String>> medicalDatabase = medicalRepository.load();

	        System.out.print("Enter medication name to update: ");
	        String medicationName = scanner.nextLine();
	        
	        boolean medicationFound = false;
	        for (Map<String, String> medicine : medicalDatabase) {
	            if (medicine.get("Medicine Name").equalsIgnoreCase(medicationName)) {
	                System.out.print("Enter new stock level for " + medicationName + ": ");
	                int newStockLevel = scanner.nextInt();
	                double newLowstockLevel = 0.2 * newStockLevel;
	                scanner.nextLine(); 
	                medicine.put("Initial Stock", String.valueOf(newStockLevel)); 
	                medicine.put("Low Stock Level Alert", String.valueOf(newLowstockLevel));
	                medicationFound = true;
	                break;
	            }
	        }
	        
	        if (!medicationFound) {
	            System.out.println("Medication " + medicationName + " not found.");
	            return;
	        }
	        medicalRepository.write(medicalDatabase);
	        System.out.println("Medication stock level updated successfully.");
	    }
}

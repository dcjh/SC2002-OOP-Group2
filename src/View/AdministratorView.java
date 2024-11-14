package View;

import Model.Gender;
import Model.UserType;
import java.io.*;
import java.util.*;
import Data.Repository.StaffRepository;
import Data.Repository.MedicineRepository;

public class AdministratorView implements UserMainView {
    private Scanner scanner = new Scanner(System.in);
    
    public void displayMenu() {
        int option = 0;
     	while (option!=5) {
             System.out.println();
             System.out.println("Administrator Menu:");
             System.out.println("1. View and Manage Hospital Staff");
             System.out.println("2. View Appointments details");
             System.out.println("3. View and Manage Medication Inventory");
             System.out.println("4. Approve Replenishment Requests");
             System.out.println("5. Logout");
             System.out.println();
             System.out.println("Select an option:");
             option = scanner.nextInt();
             
             switch(option) {
             case 1:viewandmanagerhospitalstaff(); 
             	break;
             	
             case 2:boundary.administratorView.displayscheduledAppointmentdetails();
             	break;
             	
             case 3:viewandmanagemedicationinventory();
             	break;
             	
             case 4:approvereplenishmentrequests();
             	break;
             
             case 5:System.out.println("Logging out...");
                 break;
             default:System.out.println("Invalid choice. Please try again.");
             }

         }
    }
    private void viewandmanagerhospitalstaff() {
        int option = 0;
    	while (option!=5) {
            System.out.println();
            System.out.println("View and Manage Hospital Staff:");
            System.out.println("1. Add Staff Member");
            System.out.println("2. Update Staff Member");
            System.out.println("3. Remove Staff Member");
            System.out.println("4. Display Staff list filtered by role, gender, role");
            System.out.println("5. Return to Main Menu....");
            System.out.println();
            System.out.println("Select an option:");
            option = scanner.nextInt();
            switch(option) {
            case 1:
            	Controller.administratorController.addStaffMember();
            	break;
            case 2:
            	Controller.administratorController.updateStaffmember();
            	break;
            	
            case 3:
            	Controller.administratorController.removeStaffMember();
            	break;
            	
            case 4:
				displayStaff();
				break;
            }
    	}
    	
    
    }

    
    private void viewandmanagemedicationinventory() {
    	Scanner scanner5 = new Scanner(System.in);
        int option5 = 0;
    	while (option5!=5) {
            System.out.println();
            System.out.println("View and Manage Medication Inventory:");
            System.out.println("1. Add Medication Record");
            System.out.println("2. Update Stock Level Record");
            System.out.println("3. Remove Medication");
            System.out.println("4. Display Inventory List");
            System.out.println("5. Return to Main Menu....");
            System.out.println();
            System.out.println("Select an option: ");
            option5 = scanner.nextInt();
            switch(option5) {
            case 1:
            	Controller.administratorController.addMed();
            	break;
            case 2:
            	Controller.administratorController.updateStocklevel();
            	break; 
            case 3:
            	Controller.administratorController.removeMed();
            	break;
            case 4:
            	displayMedicine();
            	break;
            }	
    	}
    
    }
    private void approvereplenishmentrequests() {
    	
    }
	
	
	public static void displayStaff() {
	    StaffRepository staffRepository = new StaffRepository();
	    List<Map<String, String>> staffDatabase = staffRepository.load();
	    Scanner scanner = new Scanner(System.in);
	    List<Map<String, String>> filteredList = new ArrayList<>(staffDatabase);
	    boolean display = true;

	    while (display) {
	        System.out.println("Select the criteria to filter:");
	        System.out.println("1. Role");
	        System.out.println("2. Gender");
	        System.out.println("3. Age Range");
	        System.out.println("4. Display");
	        System.out.print("Enter choice: ");
	        int choice = scanner.nextInt();
	        scanner.nextLine();

	        switch (choice) {
	            case 1:
	                System.out.println("Select role:");
	                for (UserType role : UserType.values()) {
	                    System.out.println((role.ordinal() + 1) + ". " + role);
	                }
	                System.out.print("Enter choice (1-" + UserType.values().length + "): ");
	                int roleChoice = scanner.nextInt();
	                scanner.nextLine();

	                if (roleChoice >= 1 && roleChoice <= UserType.values().length) {
	                    UserType selectedRole = UserType.values()[roleChoice - 1];
	                    filteredList.removeIf(staff -> !staff.get("role").equalsIgnoreCase(selectedRole.name()));
	                } else {
	                    System.out.println("Invalid role choice.");
	                }
	                break;

	            case 2:
	                System.out.println("Select gender:");
	                for (Gender gender : Gender.values()) {
	                    System.out.println((gender.ordinal() + 1) + ". " + gender);
	                }
	                System.out.print("Enter choice (1-" + Gender.values().length + "): ");
	                int genderChoice = scanner.nextInt();
	                scanner.nextLine();

	                if (genderChoice >= 1 && genderChoice <= Gender.values().length) {
	                    Gender selectedGender = Gender.values()[genderChoice - 1];
	                    filteredList.removeIf(staff -> !staff.get("gender").equalsIgnoreCase(selectedGender.name()));
	                } else {
	                    System.out.println("Invalid gender choice.");
	                }
	                break;

	            case 3:
	                System.out.print("Enter minimum age: ");
	                int minAge = scanner.nextInt();
	                System.out.print("Enter maximum age: ");
	                int maxAge = scanner.nextInt();
	                scanner.nextLine();
	                filteredList.removeIf(staff -> {
	                    int age = Integer.parseInt(staff.get("age"));
	                    return age < minAge || age > maxAge;
	                });
	                break;

	            case 4:
	                display = false;

	                if (filteredList.isEmpty()) {
	                    System.out.println("No staff members found with the specified criteria.");
	                } else {
                        System.out.println("\nDisplay Staff List");
                        System.out.printf("%-15s %-20s %-20s %-15s %-5s%n", "ID", "Name", "Role", "Gender", "Age");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------");

                        for (Map<String, String> staff : filteredList) {
                            System.out.printf("%-15s %-20s %-20s %-15s %-5s%n",
                                    staff.get("staffID"), staff.get("name"), staff.get("role"),
                                    staff.get("gender"), staff.get("age"));
	                    }
	                }
	                break;

	            default:
	                System.out.println("Invalid choice. Please try again.");
	                break;
	        }
	    }
	}
	
	public static void displayMedicine() {
        MedicineRepository medicineRepository = new MedicineRepository();
        List<Map<String, String>> medicineDatabase = medicineRepository.load();

        System.out.println("\nDisplay Current Medicine");
        System.out.printf("%-20s %-15s %-20s%n", "Medicine Name", "Initial Stock", "Low Stock Level Alert");
        System.out.println("---------------------------------------------------------------");
        for (Map<String, String> medicine : medicineDatabase) {
            System.out.printf("%-20s %-15s %-20s%n",
                    medicine.get("Medicine Name"),
                    medicine.get("Initial Stock"),
                    medicine.get("Low Stock Level Alert"));
        }
        
	}
	
	public static void displayscheduledAppointmentdetails() {
    	System.out.println("viewappointmentsdetails");	
    }

}

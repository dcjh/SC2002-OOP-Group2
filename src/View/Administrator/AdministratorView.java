package View;

import Model.User;
import java.util.*;
import Controller.AdministratorController;

public class AdministratorView implements UserMainView {
    private Scanner scanner = new Scanner(System.in);
	private User user;
	private AdministratorController administratorController;
	
    public AdministratorView(User user) {
        this.user = user;
        this.administratorController = new AdministratorController(this); 
    }
    
    
    public void displayMenu() {
        int option = 0;
     	while (option!=6) {
             System.out.println();
             System.out.println("Administrator Menu:");
             System.out.println("1. View and Manage Hospital Staff");
             System.out.println("2. View Appointments details");
             System.out.println("3. View and Manage Medication Inventory");
             System.out.println("4. Approve Replenishment Requests");
             System.out.println("5. View Replenishment Requests");
             System.out.println("6. Logout");
             System.out.println();
             System.out.println("Select an option:");
             option = scanner.nextInt();
             
             switch(option) {
             case 1:viewandmanagerhospitalstaff(); 
             	break;
             	
             case 2:administratorController.displayscheduledAppointmentdetails();
             	break;
             	
             case 3:viewandmanagemedicationinventory();
             	break;
             	
             case 4:administratorController.approveReplenishmentRequest();
             	break;
             
             case 5:administratorController.viewAllReplenishmentRequests();
                 break;
                 
             case 6: break;
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
            	administratorController.addStaffMember();
            	break;
            case 2:
            	administratorController.updateStaffMember();
            	break;
            	
            case 3:
            	administratorController.removeStaffMember();
            	break;
            	
            case 4:
            	administratorController.displayStaff();
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
            	administratorController.addMed();
            	break;
            case 2:
            	administratorController.updateMed();
            	break; 
            case 3:
            	administratorController.removeMed();
            	break;
            case 4:
            	administratorController.displayInventory();
            	break;
            }	
    	}
    
    }
}

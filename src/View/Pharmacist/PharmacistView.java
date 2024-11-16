package View.Pharmacist;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Controller.AdministratorController;
import Controller.AppointmentOutcomeController;
import Controller.InventoryController;
import Controller.PharmacistController;
import Model.User;

public class PharmacistView implements UserMainView{
    private User user;
    private PharmacistController pharmacistController;
    
	public PharmacistView(User user) {
        this.user = user;
        this.pharmacistController = new PharmacistController();
    }
	
    public PharmacistView(PharmacistController pharmacistController) {
        this.pharmacistController = pharmacistController;
    }
	
    Scanner scanner = new Scanner(System.in);
    public void menu(String hosID) {
    	int option = 0;
    	while (option!=6) {
            System.out.println();
            System.out.println("Pharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record ");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. View Replenishment Request");
            System.out.println("6. Logout....");
            System.out.println();
            System.out.println("Select an option:");
            option = scanner.nextInt();
            switch(option) {
            case 1:pharmacistController.displayAppointmentOutcome(); break;
            case 2:
            	Scanner scanner1 = new Scanner(System.in);
            	System.out.println("Key in the patientID to dispense medicine");
            	String patientID = scanner1.nextLine();
            	pharmacistController.updatePrescriptionStatus(patientID);break;
            case 3:pharmacistController.displayInventory();break;
            case 4:pharmacistController.submitReplenishmentRequests();break;
            case 5: pharmacistController.viewAllReplenishmentRequests();break;
            case 6: break;
    	}  
     }
    }
}

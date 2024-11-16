package Controller;

import java.util.Scanner;
import Data.DataAccess.InventoryDAO;
import Model.AppointmentOutcome;
import Model.Inventory;
import Model.PrescribedMedication;

public class PharmacistController {

    private AppointmentOutcomeController appointmentOutcomeController;
    private InventoryController inventoryController;
    private ReplenishmentRestockController replenishmentRestockcontroller;
    private PharmacistView pharmacistView
    
    public PharmacistController() {
        this.appointmentOutcomeController = new AppointmentOutcomeController(this);
        this.inventoryController = new InventoryController(this);
        this.replenishmentRestockcontroller = new ReplenishmentRestockController(this);
	this.pharmacistView = new PharmacistView(this);
    }
		
    public static void updatePrescriptionstatus(AppointmentOutcome appointmentOutcome) {
        if (appointmentOutcome == null) {
            System.out.println("No appointment outcome available to update.");
            return;
        }
        
        System.out.println("Prescribed Medications:");
        for (PrescribedMedication medication : appointmentOutcome.getPrescribedMedications()) {
            System.out.printf("  - %s (Status: %s)%n", medication.getMedicineName(), medication.getStatus());
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the name of the medication to update: ");
        String medicationName = scanner.nextLine();
        
        boolean updated = false;
        for (PrescribedMedication medication : appointmentOutcome.getPrescribedMedications()) {
            if (medication.getMedicineName().equalsIgnoreCase(medicationName)) {
            	InventoryDAO inventoryDAO = new InventoryDAO();
				Inventory inventory = inventoryDAO.find(medicationName,null);
                if (inventory == null) {
                    System.out.println("Medication not found in inventory.");
                    return;
                }
             
                int remainingStock = inventory.getCurrentStock() - medication.getQuantity();
                
                if (remainingStock < 0) {
                    System.out.printf("Insufficient stock for %s. Requested: %d, Available: %d. Status remains 'pending'.%n",
                        medication.getMedicineName(), medication.getQuantity(), inventory.getCurrentStock());
                    return;
                }
                
                
                if (remainingStock < inventory.getLowStockAlert()) {
                    System.out.printf("Stock for %s will fall below the low stock level after dispensing. Status remains 'pending'.%n",
                        medication.getMedicineName());
                    return;
                }
                

                medication.setStatusDispensed();
                inventory.setCurrentStock(inventory.getCurrentStock() - medication.getQuantity());
                System.out.println("Current Stock: " + inventory.getCurrentStock());
                System.out.println("Requested Quantity: " + medication.getQuantity());

                inventoryDAO.save(inventory);
                updated = true;
                System.out.printf("Status of %s updated to 'dispensed'. Remaining stock: %d%n",
                    medication.getMedicineName(), inventory.getCurrentStock());
                break;
            }
        }

        if (!updated) {
            System.out.println("Medication not found in the prescription.");
        }
    }
   
    
	public void displayInventory() {
		inventoryController.viewAllInventory();
    }
	
	public void displayAppointmentOutcome() {
		appointmentOutcomeController.printAppointmentOutcome();
	}  
    
	public void submitReplenishmentRequests() {
	    System.out.println("\n--- Pharmacist: Process Replenishment Requests ---");
	    replenishmentRestockcontroller.submitReplenishmentRequest();
	}
	
	public void viewAllReplenishmentRequests() {
	    replenishmentRestockcontroller.viewAllReplenishmentRequests();
	}
    
    
}

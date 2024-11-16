package Controller;

import java.util.List;
import java.util.Scanner;
import Data.DataAccess.InventoryDAO;
import Model.AppointmentOutcome;
import Model.Inventory;
import Model.PrescribedMedication;
import View.PharmacistView;
import Data.DataAccess.AppointmentOutcomeDAO;

public class PharmacistController {
	
    private AppointmentOutcomeController appointmentOutcomeController;
    private InventoryController inventoryController;
    private ReplenishmentRestockController replenishmentRestockcontroller;
    private PharmacistView pharmacistView;
    private AppointmentOutcomeDAO appointmentOutcomeDAO;
    

    
    public PharmacistController() {
        this.appointmentOutcomeController = new AppointmentOutcomeController(this);
        this.inventoryController = new InventoryController(this);
        this.replenishmentRestockcontroller = new ReplenishmentRestockController(this);
        this.pharmacistView = new PharmacistView(this);
    }
		
    public void updatePrescriptionStatus(String patientId) {
        List<AppointmentOutcome> outcomes = appointmentOutcomeController.getAppointmentOutcomeByPatientID(patientId);

        if (outcomes.isEmpty()) {
            System.out.println("No appointment outcomes found for the patient ID: " + patientId);
            return;
        }

        System.out.println("Appointment Outcomes for Patient ID: " + patientId);
        System.out.println("----------------------------------------------------");

        for (int i = 0; i < outcomes.size(); i++) {
            System.out.printf("%d. Appointment Outcome ID: %s, Date: %s, Time: %s%n",
                    i + 1, outcomes.get(i).getAppointmentOutcomeID(), outcomes.get(i).getDate(), outcomes.get(i).getTime());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select the appointment outcome to update (enter number): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice < 1 || choice > outcomes.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        AppointmentOutcome selectedOutcome = outcomes.get(choice - 1);

        System.out.println("Prescribed Medications:");
        for (PrescribedMedication medication : selectedOutcome.getPrescribedMedications()) {
            System.out.printf("  - %s (Status: %s)%n", medication.getMedicineName(), medication.getStatus());
        }

        System.out.print("\nEnter the name of the medication to update: ");
        String medicationName = scanner.nextLine();

        boolean updated = false;
        for (PrescribedMedication medication : selectedOutcome.getPrescribedMedications()) {
            if (medication.getMedicineName().equalsIgnoreCase(medicationName)) {
                Inventory inventory = inventoryController.findMedicineByName(medicationName);
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
                inventory.setCurrentStock(remainingStock);
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
		List<AppointmentOutcome> outcomes = appointmentOutcomeController.getAppointmentOutcomes();
		if (outcomes.isEmpty()) {
	            System.out.println("No appointment outcomes available.");
	            return;
	        }
		System.out.println("Appointment Outcomes:");
		System.out.println("----------------------------------------------------");

	    for (AppointmentOutcome outcome : outcomes) {
	        System.out.printf("Outcome ID: %s%n", outcome.getAppointmentOutcomeID());
	        System.out.printf("Doctor ID: %s%n", outcome.getDoctorID());
	        System.out.printf("Patient ID: %s%n", outcome.getPatientID());
	        System.out.printf("Appointment ID: %s%n", outcome.getAppointmentID());
	        System.out.printf("Date: %s%n", outcome.getDate());
	        System.out.printf("Time: %s%n", outcome.getTime());
	        System.out.printf("Type of Service: %s%n", outcome.getTypeOfService());
	        System.out.printf("Consultation Notes: %s%n", outcome.getConsultationNotes());

	        System.out.println("Prescribed Medications:");
	           	for (PrescribedMedication med : outcome.getPrescribedMedications()) {
	                System.out.printf("  - %s (Qty: %d, Status: %s)%n",
	                        med.getMedicineName(),
	                        med.getQuantity(),
	                        med.getStatus());
	            }
	            System.out.println("----------------------------------------------------");
	        }
	}  
    
	public void submitReplenishmentRequests() {
	    System.out.println("\n--- Pharmacist: Process Replenishment Requests ---");
	    replenishmentRestockcontroller.submitReplenishmentRequest();
	}
	
	public void viewAllReplenishmentRequests() {
	    replenishmentRestockcontroller.viewAllReplenishmentRequests();
	}
    
    
}

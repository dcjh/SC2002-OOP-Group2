package Controller;

import Data.DataAccess.AppointmentOutcomeDAO;
import Model.Shared.AppointmentOutcome;
import Model.Shared.Inventory;
import Model.Shared.PrescribedMedication;
import View.Pharmacist.PharmacistView;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PharmacistController {
	
    private AppointmentOutcomeController appointmentOutcomeController;
    private InventoryController inventoryController;
    private ReplenishmentRestockController replenishmentRestockcontroller;
    private PharmacistView pharmacistView;
    private AppointmentOutcomeDAO appointmentOutcomeDAO;
    

    
    public PharmacistController() {
        this.appointmentOutcomeController = new AppointmentOutcomeController();
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

        // to store new prescribed medication that has updated status
        ArrayList<PrescribedMedication> newPrescribedMedications = new ArrayList<>();
        PrescribedMedication newMedication;

        System.out.println("Prescribed Medications:");

        for (PrescribedMedication medication : selectedOutcome.getPrescribedMedications()) {
            System.out.printf("  - %s (Qty: %d, Status: %s)%n",
                    medication.getMedicineName(), medication.getQuantity(), medication.getStatus());

            Inventory inventory = inventoryController.findMedicineByName(medication.getMedicineName());
            if (inventory == null) {
                System.out.printf("Medicine '%s' not found in inventory. Status remains 'pending'.%n", medication.getMedicineName());
                newPrescribedMedications.add(medication);
                continue;
            }

            int remainingStock = inventory.getCurrentStock() - medication.getQuantity();

            if (remainingStock < 0) {
                System.out.printf("Insufficient stock for %s. Requested: %d, Available: %d. Status remains 'pending'.%n",
                        medication.getMedicineName(), medication.getQuantity(), inventory.getCurrentStock());
                newPrescribedMedications.add(medication);
                continue;
            }

            // to store new prescribed medication that has dispensed as status

            newMedication = new PrescribedMedication(medication.getMedicineName(), medication.getQuantity());
            newMedication.setStatusDispensed();
            newPrescribedMedications.add(newMedication);

            // decrease inventory
            inventory.setCurrentStock(remainingStock);
            inventoryController.updateDispensestockLevel(medication.getMedicineName(), medication.getQuantity()); 

            System.out.printf("Status of %s updated to 'dispensed'. Remaining stock: %d%n",
                    medication.getMedicineName(), inventory.getCurrentStock());
        }
        // set new prescribed medication new appointment outcome
            appointmentOutcomeController.setPrescribedMedicineUpdatedStatus(selectedOutcome.getAppointmentOutcomeID(), newPrescribedMedications);
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

/*in main uml and controller uml
contains: 
+ requestRestock() 
+ updatePrescriptionstatus() 
uses Pharmacist, InventoryController, ResotckRequestController, AppointmentOutcomeRecordeController
used by PharmacistView
*/

package Controller;
import Model.Roles.Pharmacist;
import Model.Shared.Medicine;
import Model.Shared.AppointmentOutcome;
import View.PharmacistView;
import Data.DataAccess.InventoryDAO;
import Data.DataAccess.ReplenishmentDAO;
import Model.AppointmentOutcome;
import Model.Inventory;
import Model.PrescribedMedication;
import Model.ReplenishmentRequest;


public class PharmacistController{

    private static InventoryDAO inventoryDAO;
    public static void setInventoryDAO(InventoryDAO dao) {
        inventoryDAO = dao;
    }
	
    private Pharmacist pharmacist;
    private PharmacistView view;
    private PharmacistRepository repository;

    public PharmacistController(Pharmacist pharmacist, PharmacistView view, PharmacistRepository repository){
        this.pharmacist = pharmacist;
        this.view = view;
        this.repository = repository;
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
    
    public static void submitReplenishmentRequest() {
        InventoryDAO inventoryDAO = new InventoryDAO();
        ReplenishmentDAO replenishmentDAO = new ReplenishmentDAO();
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("\nEnter Inventory ID for replenishment request: ");
        String inventoryID = scanner.nextLine();
        Inventory selectedItem = inventoryDAO.find(inventoryID, null);

        if (selectedItem == null) {
            System.out.println("Invalid Inventory ID.");
            return;
        }

        System.out.print("Enter requested quantity: ");
        int requestedQuantity = scanner.nextInt();
        scanner.nextLine(); 
        String requestID = replenishmentDAO.generateNextRequestID();
        ReplenishmentRequest request = new ReplenishmentRequest(
                requestID, selectedItem.getMedicineID(), selectedItem.getName(), requestedQuantity, "Pending"
        );
        
        replenishmentDAO.save(request);
        System.out.println("Replenishment request submitted successfully with Request ID: " + requestID);

    }
}


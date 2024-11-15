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
import Data.Repository.PharmacistRepository;

public class PharmacistController{
    private Pharmacist pharmacist;
    private PharmacistView view;
    private PharmacistRepository repository;

    public PharmacistController(Pharmacist pharmacist, PharmacistView view, PharmacistRepository repository){
        this.pharmacist = pharmacist;
        this.view = view;
        this.repository = repository;
    }
    
   public static void updatePrescriptionstatus() {
    	System.out.println("Functionality in progress");
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



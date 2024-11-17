package Controller;
import Data.DataAccess.InventoryDAO;
import Data.DataAccess.ReplenishmentDAO;
import Model.Shared.Inventory;
import Model.Shared.ReplenishmentRequest;
import java.util.List;
import java.util.Scanner;

public class ReplenishmentRestockController {
    private final ReplenishmentDAO replenishmentDAO = new ReplenishmentDAO();
    private final InventoryDAO inventoryDAO = new InventoryDAO();
    private AdministratorController administratorController;
    private PharmacistController phamarcistController;
    
    public ReplenishmentRestockController(AdministratorController administratorController) {
    	this.administratorController = administratorController;
    }
    
    public ReplenishmentRestockController(PharmacistController phamarcistController) {
    	this.phamarcistController = phamarcistController;
    }
    
    public void viewAllReplenishmentRequests() {
        List<ReplenishmentRequest> allRequests = replenishmentDAO.loadAll();
        printReplenishmentRequests("All Replenishment Requests", allRequests);
    }
    
    public void viewPendingRequests() {
        List<ReplenishmentRequest> pendingRequests = replenishmentDAO.loadAll().stream()
                .filter(req -> req.getStatus().equalsIgnoreCase("Pending"))
                .toList();
        printReplenishmentRequests("Pending Replenishment Requests", pendingRequests);
    }
    
    public void viewApprovedRequests() {
        List<ReplenishmentRequest> approvedRequests = replenishmentDAO.loadAll().stream()
                .filter(req -> req.getStatus().equalsIgnoreCase("Approved"))
                .toList();
        printReplenishmentRequests("Approved Replenishment Requests", approvedRequests);
    }
    
    public void viewRejectedRequests() {
        List<ReplenishmentRequest> rejectedRequests = replenishmentDAO.loadAll().stream()
                .filter(req -> req.getStatus().equalsIgnoreCase("Denied") || req.getStatus().equalsIgnoreCase("Denied"))
                .toList();
        printReplenishmentRequests("Rejected Replenishment Requests", rejectedRequests);
    }
	
    public void submitReplenishmentRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter Medicine Name for replenishment request: ");
        String inventoryName = scanner.nextLine();
        Inventory selectedItem = inventoryDAO.find(inventoryName, null);

        if (selectedItem == null) {
            System.out.println("Invalid Inventory Name.");
            return;
        }

        System.out.print("Enter requested quantity: ");
        int requestedQuantity = scanner.nextInt();
        scanner.nextLine();

        String requestID = replenishmentDAO.generateNextRequestID();
        ReplenishmentRequest request = new ReplenishmentRequest(
                requestID, selectedItem.getName(), requestedQuantity, "Pending"
        );

        replenishmentDAO.save(request);
        System.out.println("Replenishment request submitted successfully with Request ID: " + requestID);
    }
    
    public void processReplenishmentRequests() {
        Scanner scanner = new Scanner(System.in);

        viewPendingRequests();
        System.out.print("\nEnter Request ID to process: ");
        String requestID = scanner.nextLine().trim();

        ReplenishmentRequest selectedRequest = replenishmentDAO.find(requestID, null);

        if (selectedRequest == null) {
            System.out.println("Invalid Request ID.");
            return;
        }

        System.out.print("Approve or Deny this request? (A/D): ");
        String decision = scanner.nextLine();

        if (decision.equalsIgnoreCase("A")) {
            Inventory inventory = inventoryDAO.find(selectedRequest.getInventoryName(), null);
            if (inventory != null) {
                inventory.setCurrentStock(inventory.getCurrentStock() + selectedRequest.getRequestedQuantity());
                inventoryDAO.save(inventory);
            }
            selectedRequest.setStatus("Approved");
            replenishmentDAO.save(selectedRequest);
            System.out.println("Request approved, and inventory updated.");
        } else if (decision.equalsIgnoreCase("D")) {
            selectedRequest.setStatus("Denied");
            replenishmentDAO.save(selectedRequest);
            System.out.println("Request denied.");
        } else {
            System.out.println("Invalid choice. Request not processed.");
        }
    }

    private void printReplenishmentRequests(String title, List<ReplenishmentRequest> requests) {
        System.out.println("\n--- " + title + " ---");
        if (requests.isEmpty()) {
            System.out.println("No requests found.");
        } else {
            System.out.printf("%-10s %-20s %-20s %-15s%n", "Request ID", "Inventory Name", "Requested Qty", "Status");
            System.out.println("--------------------------------------------------------------------------------");
            for (ReplenishmentRequest req : requests) {
                System.out.printf("%-10s %-20s %-20d %-15s%n",
                        req.getRequestID(),
                        req.getInventoryName(),
                        req.getRequestedQuantity(),
                        req.getStatus());
            }
        }
    }
    
}

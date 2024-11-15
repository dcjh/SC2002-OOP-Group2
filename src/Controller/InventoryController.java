package Controller;
import Data.DataAccess.InventoryDAO;
import Data.DataAccess.ReplenishmentDAO;
import Model.Inventory;
import Model.ReplenishmentRequest;

import java.util.List;
import java.util.Scanner;

public class InventoryController {
    private final static InventoryDAO inventoryDAO = new InventoryDAO();
    
    public static void addMedicine() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Medicine Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Initial Stock: ");
        int initialStock = scanner.nextInt();
        int currentStock = initialStock;
        int lowStockAlert = (int) (0.2 * initialStock);

        Inventory medicine = new Inventory(name, initialStock, currentStock, lowStockAlert);
        inventoryDAO.save(medicine);

        System.out.println("Medicine added successfully.");
    
    }
    
    public static void removeMedicine() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Medicine Name to remove: ");
        String medicineName = scanner.nextLine();

        inventoryDAO.delete(medicineName, null);
        System.out.println("Medicine removed successfully......");
    }
    
    public static void updateRestockLevel() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Medicine name to update restock: ");
        String medicineName = scanner.nextLine();
        Inventory medicine = inventoryDAO.find(medicineName, null);

        if (medicine != null) {
            System.out.print("Enter quantity to add to stock level: ");
            int quantity = scanner.nextInt();
            int newStockLevel = medicine.getCurrentStock() + quantity;
            medicine.setCurrentStock(newStockLevel);
            inventoryDAO.save(medicine);

            System.out.println("Stock level updated successfully.");
        } else {
            System.out.println("Medicine with ID " + medicineName + " not found.");
        }
    }
    
    
    public static void updateDispensestockLevel() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Medicine name to update dispense: ");
        String medicineName = scanner.nextLine();
        Inventory medicine = inventoryDAO.find(medicineName, null);

        if (medicine != null) {
            System.out.print("Enter quantity to dispense: ");
            int quantity = scanner.nextInt();
            int newStockLevel = medicine.getCurrentStock() - quantity;
            medicine.setCurrentStock(newStockLevel);
            inventoryDAO.save(medicine);

            System.out.println("Stock level updated successfully.");
        } else {
            System.out.println("Medicine, " + medicineName + " not found.");
        }
    }
    
    
    public static void processReplenishmentRequests() {
        ReplenishmentDAO replenishmentDAO = new ReplenishmentDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();
        Scanner scanner = new Scanner(System.in);

        // Load all pending requests
        List<ReplenishmentRequest> pendingRequests = replenishmentDAO.loadAll().stream()
                .filter(req -> req.getStatus().equalsIgnoreCase("Pending"))
                .toList();

        if (pendingRequests.isEmpty()) {
            System.out.println("No pending replenishment requests.");
            return;
        }

        System.out.println("\nPending Replenishment Requests:");
        System.out.printf("%-10s %-15s %-20s %-15s %-10s%n", "Request ID", "Inventory ID", "Inventory Name", "Requested Qty", "Status");
        System.out.println("--------------------------------------------------------------------------------");
        for (ReplenishmentRequest request : pendingRequests) {
            System.out.printf("%-10s %-15s %-20s %-15d %-10s%n",
                    request.getRequestID(),
                    request.getMedicineID(),
                    request.getInventoryName(),
                    request.getRequestedQuantity(),
                    request.getStatus());
        }

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
            Inventory inventory = inventoryDAO.find(selectedRequest.getMedicineID(), null);
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
}

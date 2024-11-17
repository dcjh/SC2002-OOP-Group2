package Controller;
import Data.DataAccess.InventoryDAO;
import Model.Shared.Inventory;
import View.Pharmacist.InventoryView;
import java.util.List;
import java.util.Scanner;

public class InventoryController {
    private final static InventoryDAO inventoryDAO = new InventoryDAO();
    InventoryView view = new InventoryView();
	private AdministratorController administratorController;
	private PharmacistController pharmacistController;
    
    public InventoryController(AdministratorController administratorController) {
    	this.administratorController = administratorController;
    }
    
    public InventoryController( PharmacistController pharmacistController) {
    	this.pharmacistController = pharmacistController;
    }


    public void addMedicine() {
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
    
    public void removeMedicine() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Medicine Name to remove: ");
        String medicineName = scanner.nextLine();

        inventoryDAO.delete(medicineName, null);
        System.out.println("Medicine removed successfully......");
    }
    
    public void updateRestockLevel() {
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
    
    
    
    /** 
     * @param medicineName
     * @param quantity
     */
    public void updateDispensestockLevel(String medicineName, int quantity) {
        // Scanner scanner = new Scanner(System.in);

        // System.out.print("Enter Medicine name to update dispense: ");
        // String medicineName = scanner.nextLine();
        Inventory medicine = inventoryDAO.find(medicineName, null);

        if (medicine != null) {
            // System.out.print("Enter quantity to dispense: ");
            // int quantity = scanner.nextInt();
            int newStockLevel = medicine.getCurrentStock() - quantity;
            medicine.setCurrentStock(newStockLevel);
            inventoryDAO.save(medicine);

            System.out.println("Stock level updated successfully.");
        } else {
            System.out.println("Medicine, " + medicineName + " not found.");
        }
    }
    
    public void viewAllInventory() {
        List<Inventory> inventories = inventoryDAO.loadAll();
        if (inventories.isEmpty()) {
            System.out.println("No inventory records available.");
            return;
        }

        System.out.println("\nDisplaying all inventory:");
        System.out.printf("%-20s %-20s %-20s %-20s%n", "Inventory Name", "Initial Stock", "Current Stock", "Low Stock Alert");
        System.out.println("--------------------------------------------------------------------------------");
        for (Inventory i : inventories) {
            printInventory(i);
            System.out.println();
        }
    }
    
    
    /** 
     * @param medicineName
     * @return Inventory
     */
    public Inventory findMedicineByName(String medicineName) {
        Inventory medicine = inventoryDAO.find(medicineName, null);
        if (medicine == null) {
            System.out.println("Medicine with name \"" + medicineName + "\" not found.");
        }
        return medicine;
    }
    
    public void printInventory(Inventory inv) {
        this.view.printInventory(inv.getName(), inv.getInitialStock(), inv.getCurrentStock(), inv.getLowStockAlert());
    }
   
    
}

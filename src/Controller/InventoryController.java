package Controller;

import Data.DataAccess.InventoryDAO;
import Model.Shared.Inventory;
import View.Pharmacist.InventoryView;
import java.util.List;
import java.util.Scanner;

/**
 * The InventoryController class is responsible for managing the inventory of medicines.
 * This includes adding, removing, updating, and viewing inventory, as well as managing stock levels.
 */
public class InventoryController {
    private final static InventoryDAO inventoryDAO = new InventoryDAO();
    InventoryView view = new InventoryView();
    private AdministratorController administratorController;
    private PharmacistController pharmacistController;

    /**
     * Constructs an InventoryController for an administrator.
     *
     * @param administratorController the AdministratorController instance
     */
    public InventoryController(AdministratorController administratorController) {
        this.administratorController = administratorController;
    }

    /**
     * Constructs an InventoryController for a pharmacist.
     *
     * @param pharmacistController the PharmacistController instance
     */
    public InventoryController(PharmacistController pharmacistController) {
        this.pharmacistController = pharmacistController;
    }

    /**
     * Adds a new medicine to the inventory.
     */
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

    /**
     * Removes a medicine from the inventory.
     */
    public void removeMedicine() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Medicine Name to remove: ");
        String medicineName = scanner.nextLine();

        inventoryDAO.delete(medicineName, null);
        System.out.println("Medicine removed successfully......");
    }

    /**
     * Updates the restock level for a specific medicine.
     */
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
     * Updates the stock level after dispensing a specific quantity of medicine.
     *
     * @param medicineName the name of the medicine to update
     * @param quantity     the quantity to be dispensed
     */
    public void updateDispensestockLevel(String medicineName, int quantity) {
        Inventory medicine = inventoryDAO.find(medicineName, null);

        if (medicine != null) {
            int newStockLevel = medicine.getCurrentStock() - quantity;
            medicine.setCurrentStock(newStockLevel);
            inventoryDAO.save(medicine);

            System.out.println("Stock level updated successfully.");
        } else {
            System.out.println("Medicine, " + medicineName + " not found.");
        }
    }

    /**
     * Displays all inventory records in the system.
     */
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
     * Finds a medicine by its name in the inventory.
     *
     * @param medicineName the name of the medicine to find
     * @return the Inventory object if found, otherwise null
     */
    public Inventory findMedicineByName(String medicineName) {
        Inventory medicine = inventoryDAO.find(medicineName, null);
        if (medicine == null) {
            System.out.println("Medicine with name \"" + medicineName + "\" not found.");
        }
        return medicine;
    }

    /**
     * Prints the details of a specific inventory item.
     *
     * @param inv the Inventory object to print
     */
    public void printInventory(Inventory inv) {
        this.view.printInventory(inv.getName(), inv.getInitialStock(), inv.getCurrentStock(), inv.getLowStockAlert());
    }
}

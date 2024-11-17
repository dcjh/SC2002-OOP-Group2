package Data.DataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Model.Shared.Inventory;

/**
 * The InventoryDAO class handles the data access operations for the inventory.
 * This includes loading, saving, finding, and deleting inventory records from the CSV file.
 */
public class InventoryDAO {
    private static final String FILE_PATH = "C:\\Users\\chuaz\\eclipse-workspace\\OOP_project\\src\\Administrator\\Medicine_List.csv";

    /**
     * Loads all inventory items from the CSV file.
     *
     * @return a list of all inventory items
     */
    public List<Inventory> loadAll() {
        List<Inventory> inventoryList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = br.readLine(); // Read and skip header line
            if (line == null) {
                System.out.println("The file is empty.");
                return inventoryList;
            }

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != 4) { 
                    System.err.println("Skipping malformed row: " + line);
                    continue;
                }
                try {
                    String name = values[0].trim();
                    int initialStock = Integer.parseInt(values[1].trim());
                    int currentStock = Integer.parseInt(values[2].trim());
                    int lowStockAlert = Integer.parseInt(values[3].trim());

                    Inventory inventory = new Inventory(name, initialStock, currentStock, lowStockAlert);
                    inventoryList.add(inventory);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing numeric value in row: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading medicines from file: " + FILE_PATH + ". Details: " + e.getMessage());
        }
        return inventoryList;
    }

    /**
     * Saves an inventory item to the CSV file, either by appending or updating an existing record.
     *
     * @param item the inventory item to save
     */
    public void save(Inventory item) {
        List<Inventory> inventoryList = loadAll();
        boolean exists = false;

        // Check if the inventory item already exists
        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).getName().equalsIgnoreCase(item.getName())) {
                inventoryList.set(i, item); // Update existing inventory item
                exists = true;
                break;
            }
        }

        // If the inventory item doesn't exist, add it to the list
        if (!exists) {
            inventoryList.add(item);
        }

        // Save the updated inventory list back to the CSV file
        saveAll(inventoryList);
    }

    /**
     * Finds an inventory item by its name.
     *
     * @param name      the name of the inventory item to find
     * @param searchKey the search key (optional, not used in this implementation)
     * @return the inventory item if found, otherwise null
     */
    public Inventory find(String name, String searchKey) {
        List<Inventory> inventoryList = loadAll();
        for (Inventory inventory : inventoryList) {
            if (inventory.getName().equalsIgnoreCase(name)) {
                return inventory;
            }
        }
        return null;
    }

    /**
     * Deletes an inventory item by its name.
     *
     * @param name      the name of the inventory item to delete
     * @param searchKey the search key (optional, not used in this implementation)
     */
    public void delete(String name, String searchKey) {
        List<Inventory> inventoryList = loadAll();
        inventoryList.removeIf(inventory -> inventory.getName().equalsIgnoreCase(name));
        saveAll(inventoryList);
    }

    /**
     * Saves the entire inventory list to the CSV file.
     *
     * @param inventoryList the list of inventory items to save
     */
    private void saveAll(List<Inventory> inventoryList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            writer.write("Medicine Name,Initial Stock,Current Stock,Low Stock Level Alert");
            writer.newLine();
            for (Inventory inventory : inventoryList) {
                writer.write(formatInventoryCSVLine(inventory));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Formats an inventory item as a CSV line.
     *
     * @param inventory the inventory item to format
     * @return the formatted CSV line representing the inventory item
     */
    private String formatInventoryCSVLine(Inventory inventory) {
        return String.join(",",
                inventory.getName(),
                String.valueOf(inventory.getInitialStock()),
                String.valueOf(inventory.getCurrentStock()),
                String.valueOf(inventory.getLowStockAlert())
        );
    }
}

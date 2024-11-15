package Data.DataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Model.Inventory;

public class InventoryDAO implements DataAccessObject<Inventory, String> {
    private static final String FILE_PATH = "C:\\Users\\chuaz\\eclipse-workspace\\OOP_project\\src\\Administrator\\Medicine_List.csv";

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

    public void save(Inventory item) {
        List<Inventory> inventoryList = loadAll();
        boolean exists = false;

        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).getName().equalsIgnoreCase(item.getName())) {
                inventoryList.set(i, item); 
                exists = true;
                break;
            }
        }

        if (!exists) {
            inventoryList.add(item); 
        }

        saveAll(inventoryList);
    }

    public Inventory find(String name,String searchKey) {
        List<Inventory> inventoryList = loadAll();
        for (Inventory inventory : inventoryList) {
            if (inventory.getName().equalsIgnoreCase(name)) {
                return inventory;
            }
        }
        return null;
    }

    public void delete(String name, String searchKey) {
        List<Inventory> inventoryList = loadAll();
        inventoryList.removeIf(inventory -> inventory.getName().equalsIgnoreCase(name));
        saveAll(inventoryList);
    }

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

    private String formatInventoryCSVLine(Inventory inventory) {
        return String.join(",",
                inventory.getName(),
                String.valueOf(inventory.getInitialStock()),
                String.valueOf(inventory.getCurrentStock()),
                String.valueOf(inventory.getLowStockAlert())
        );
    }

}

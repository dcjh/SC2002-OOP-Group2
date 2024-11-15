package View;
import Data.DataAccess.*;
import Model.Inventory;

import java.util.List;
public class InventoryView {
    private final static InventoryDAO inventoryDAO = new InventoryDAO();

    public static void displayInventory() {
        List<Inventory> inventoryList = inventoryDAO.loadAll();

        if (inventoryList.isEmpty()) {
            System.out.println("No inventory items found.");
        } else {
            System.out.println("\nDisplay Current Inventory");
            System.out.printf("%-15s %-20s %-15s %-20s%n", "Inventory Name","Initial Stock Level", "Current Stock", "Low Stock Level");
            System.out.println("----------------------------------------------------------------------------");
            for (Inventory item : inventoryList) {
                System.out.printf("%-15s %-20s %-15s %-20s%n",
                        item.getName(),
                        item.getInitialStock(),
                        item.getCurrentStock(),
                        item.getLowStockAlert());
            }
        }
    }
}

package View.Pharmacist;

public class InventoryView {

    /**
     * Displays the details of an inventory item.
     *
     * @param name          The name of the inventory item.
     * @param initialStock  The initial stock level of the inventory item.
     * @param currentStock  The current stock level of the inventory item.
     * @param lowStockAlert The threshold at which a low stock alert is triggered.
     */
    public void printInventory(String name, int initialStock, int currentStock, int lowStockAlert) {
        System.out.printf("%-20s %-20d %-20d %-20d%n",
                name,
                initialStock,
                currentStock,
                lowStockAlert);
    }
}

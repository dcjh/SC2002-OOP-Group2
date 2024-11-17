package View.Pharmacist;
public class InventoryView {
	
    /** 
     * @param name
     * @param initialStock
     * @param currentStock
     * @param lowStockAlert
     */
    public void printInventory(String name, int initialStock, int currentStock, int lowStockAlert) {
            System.out.printf("%-20s %-20d %-20d %-20d%n",
            		name,
            		initialStock,
            		currentStock,
            		lowStockAlert);
        }		
	}


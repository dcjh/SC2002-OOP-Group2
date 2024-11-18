package Model.Shared;

/**
 * Represents an item in the inventory, specifically a medicine with stock levels and restock alert thresholds.
 */
public class Inventory {
    private String medicineID;
    private String name;
    private int initialStock;
    private int currentStock;     
    private int lowStockAlert;

    /**
     * Creates an inventory item for a specific medicine.
     * 
     * @param name           the name of the medicine.
     * @param initialStock   the initial stock level when the medicine was added to inventory.
     * @param currentStock   the current stock level of the medicine.
     * @param lowStockAlert  the threshold at which a low stock alert should be issued.
     */
    public Inventory(String name, int initialStock, int currentStock, int lowStockAlert) {
        this.name = name;
        this.initialStock = initialStock;
        this.currentStock = currentStock;
        this.lowStockAlert = lowStockAlert;
    }

    /**
     * Gets the name of the medicine.
     * 
     * @return the name of the medicine.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the medicine.
     * 
     * @param name the new name of the medicine.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the initial stock level of the medicine when it was added to inventory.
     * 
     * @return the initial stock level.
     */
    public int getInitialStock() {
        return initialStock;
    }

    /**
     * Sets the initial stock level of the medicine.
     * 
     * @param initialStock the new initial stock level.
     */
    public void setInitialStock(int initialStock) {
        this.initialStock = initialStock;
    }

    /**
     * Gets the current stock level of the medicine.
     * 
     * @return the current stock level.
     */
    public int getCurrentStock() {
        return currentStock;
    }

    /**
     * Sets the current stock level of the medicine.
     * 
     * @param currentStock the new current stock level.
     */
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    /**
     * Gets the low stock alert threshold for the medicine.
     * 
     * @return the low stock alert threshold.
     */
    public int getLowStockAlert() {
        return lowStockAlert;
    }

    /**
     * Sets the low stock alert threshold for the medicine.
     * 
     * @param lowStockAlert the new low stock alert threshold.
     */
    public void setLowStockAlert(int lowStockAlert) {
        this.lowStockAlert = lowStockAlert;
    }
}

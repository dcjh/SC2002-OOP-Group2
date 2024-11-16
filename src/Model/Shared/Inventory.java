package Model.Shared;

public class Inventory {
    private String medicineID;
    private String name;
    private int initialStock;
    private int currentStock;     
    private int lowStockAlert;

    public Inventory(String name, int initialStock, int currentStock, int lowStockAlert) {
        this.name = name;
        this.initialStock = initialStock;
        this.currentStock = currentStock;
        this.lowStockAlert = lowStockAlert;
    }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getInitialStock() { return initialStock; }
    public void setInitialStock(int initialStock) { this.initialStock = initialStock; }

    public int getCurrentStock() { return currentStock; }
    public void setCurrentStock(int currentStock) { this.currentStock = currentStock; }

    public int getLowStockAlert() { return lowStockAlert; }
    public void setLowStockAlert(int lowStockAlert) { this.lowStockAlert = lowStockAlert; }


}

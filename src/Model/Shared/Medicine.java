package Model.Shared;

/**
 * Represents a medicine, including its name and quantity available.
 */
public class Medicine {
    protected String medicineName;
    protected int quantity;

    /**
     * Creates a new instance of Medicine with a given name and quantity.
     *
     * @param medicineName The name of the medicine.
     * @param quantity     The quantity of the medicine available.
     */
    public Medicine(String medicineName, int quantity) {
        this.medicineName = medicineName;
        this.quantity = quantity;
    }

    /**
     * Gets the name of the medicine.
     *
     * @return The name of the medicine.
     */
    public String getMedicineName() {
        return this.medicineName;
    }

    /**
     * Gets the quantity of the medicine.
     *
     * @return The quantity of the medicine available.
     */
    public int getQuantity() {
        return this.quantity;
    }
}

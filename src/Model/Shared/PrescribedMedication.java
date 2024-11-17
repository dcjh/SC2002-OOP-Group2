package Model.Shared;

/**
 * Represents a prescribed medication with a name, quantity, and status.
 * <p>
 * There are two possible values for the status of the medication: "pending" or "dispensed".
 * Initially, when an appointment outcome is created, the status is set to "pending".
 * The status is changed to "dispensed" after the medication is provided by the pharmacist.
 */
public class PrescribedMedication extends Medicine {
    private String status;

    /**
     * Creates a PrescribedMedication object with the given name and quantity.
     * The initial status of the medication is set to "pending".
     *
     * @param medicineName The name of the medicine.
     * @param quantity     The quantity of the prescribed medication.
     */
    public PrescribedMedication(String medicineName, int quantity) {
        super(medicineName, quantity);
        this.status = "pending";
    }

    /**
     * Gets the status of the medication.
     * The status could be either "pending" or "dispensed".
     *
     * @return The current status of the medication.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the medication to "dispensed".
     * This is called when the pharmacist provides the medication to the patient.
     */
    public void setStatusDispensed() {
        this.status = "dispensed";
    }

    /**
     * Returns a string representation of the prescribed medication.
     * Includes the medicine name, quantity, and current status.
     *
     * @return A formatted string with medication details.
     */
    @Override
    public String toString() {
        return String.format("%s (Quantity: %d, Status: %s)", this.medicineName, this.quantity, this.status);
    }
}

package Model.Shared;

/**
 * Represents a replenishment request for inventory items.
 * <p>
 * A replenishment request contains information about the requested inventory item, including
 * the quantity requested and the status of the request (e.g., "Approved", "Denied").
 */
public class ReplenishmentRequest {
    private String requestID;
    private String inventoryName;
    private int requestedQuantity;
    private String status; // Approved, Denied, Pending

    /**
     * Constructs a new ReplenishmentRequest object.
     *
     * @param requestID         The unique ID of the request.
     * @param inventoryName     The name of the inventory item.
     * @param requestedQuantity The quantity of the inventory item being requested.
     * @param status            The status of the request ("Approved", "Denied", or "Pending").
     */
    public ReplenishmentRequest(String requestID, String inventoryName, int requestedQuantity, String status) {
        this.requestID = requestID;
        this.inventoryName = inventoryName;
        this.requestedQuantity = requestedQuantity;
        this.status = status;
    }

    /**
     * Gets the unique ID of the replenishment request.
     *
     * @return The unique request ID.
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * Sets the unique ID of the replenishment request.
     *
     * @param requestID The unique request ID to be set.
     */
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    /**
     * Gets the name of the inventory item being requested.
     *
     * @return The name of the inventory item.
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * Sets the name of the inventory item being requested.
     *
     * @param inventoryName The name of the inventory item to be set.
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * Gets the quantity of the inventory item being requested.
     *
     * @return The requested quantity of the inventory item.
     */
    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    /**
     * Sets the quantity of the inventory item being requested.
     *
     * @param requestedQuantity The requested quantity to be set.
     */
    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    /**
     * Gets the current status of the replenishment request.
     * Possible values are "Approved", "Denied", or "Pending".
     *
     * @return The current status of the request.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the replenishment request.
     * Possible values are "Approved", "Denied", or "Pending".
     *
     * @param status The status to be set for the replenishment request.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}

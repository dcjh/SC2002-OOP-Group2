package Model.Shared;

public class ReplenishmentRequest {
    private String requestID;
    private String medicineID;
    private String inventoryName;
    private int requestedQuantity;
    private String status; //Approved, Denied
    
    public ReplenishmentRequest(String requestID, String inventoryName, int requestedQuantity, String status) {
        this.requestID = requestID;
        this.inventoryName = inventoryName;
        this.requestedQuantity = requestedQuantity;
        this.status = status;
    }
    
    
    /** 
     * @return String
     */
    public String getRequestID() 
    { 
    	return requestID; 
    	}
    
    
    /** 
     * @param requestID
     */
    public void setRequestID(String requestID) 
    { 
    	this.requestID = requestID; 
    	}

    public String getInventoryName() { 
    	return inventoryName; 
    	}
    
    public void setInventoryName(String inventoryName) { 
    	this.inventoryName = inventoryName; 
    	}

    public int getRequestedQuantity() { 
    	return requestedQuantity; 
    	}
    
    public void setRequestedQuantity(int requestedQuantity) { 
    	this.requestedQuantity = requestedQuantity; 
    	}

    public String getStatus() { 
    	return status; 
    	}
    
    public void setStatus(String status) { 
    	this.status = status; 
    	}

}

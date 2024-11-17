package Model.Shared;

public class Medicine {
    protected String medicineName;
    protected int quantity;

    public Medicine(String medicineName, int quantity){
        this.medicineName = medicineName;
        this.quantity = quantity;
    }

    
    /** 
     * @return String
     */
    public String getMedicineName(){
        return this.medicineName;
    }

    
    /** 
     * @return int
     */
    public int getQuantity(){
        return this.quantity;
    }
    
}

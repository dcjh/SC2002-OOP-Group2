package Model.Shared;

public class Medicine {
    protected String medicineName;
    protected int quantity;

    public Medicine(String medicineName, int quantity){
        this.medicineName = medicineName;
        this.quantity = quantity;
    }

    public String getMedicineName(){
        return this.medicineName;
    }

    public int getQuantity(){
        return this.quantity;
    }
    
}

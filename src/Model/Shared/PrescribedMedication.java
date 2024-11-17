package Model.Shared;

    // there are 2 possible values for status (status of medicine) ["pending", "dispensed"], if there is an event where the medicine is not in stock yet, status will be pending until stock is replenished and pharmacist is able to dispensed
    // the default value for medicine status is "pending" when Appointment Outcome is first created
    // then it is changed to "dispensed" after the med is given by the pharmacist

public class PrescribedMedication extends Medicine{
    private String status;

    public PrescribedMedication (String medicineName, int quantity){
        super(medicineName, quantity);
        this.status = "pending";
    }

    
    /** 
     * @return String
     */
    // Override toString() to print medication details
    @Override
    public String toString() {
        return String.format("%s (Quantity: %d, Status: %s)", this.medicineName, this.quantity, this.status);
    }


    
    /** 
     * @return String
     */
    public String getStatus(){
        return this.status;
    }

    public void setStatusDispensed(){
        this.status = "dispensed";
    }
}

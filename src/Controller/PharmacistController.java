/*in main uml and controller uml
contains: 
+ requestRestock() 
+ updateStatus() 
+ updatePrescriptionstatus() 
uses Pharmacist, InventoryController, ResotckRequestController, AppointmentOutcomeRecordeController
used by PharmacistView
*/

package Controller;
import Authentication.Authentication;

public class PharmacistController{
    private Pharmacist pharmacist;
    private PharmacistView view;
    private Authentication auth;

    public PharmacistController(Pharmacist pharmacist, PharmacistView view, Authentication auth){
        this.pharmacist = pharmacist;
        this.view = view;
        this.auth = auth;
    }

    public boolean logic(String hosID, String password){
        if(auth.authenticate(hosID, password) != null){
            view.displayLoginSuccess();
            return true;
        } else {
            view.displayLoginFailure();
            return false;
        }
    }
    
    public void requestRestock(){

    }

    public void updateStatus(){

    }

    public void updatePrescriptionstatus(){
        
    }
}



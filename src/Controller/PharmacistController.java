/*in main uml and controller uml
contains: 
+ requestRestock() 
+ updatePrescriptionstatus() 
uses Pharmacist, InventoryController, ResotckRequestController, AppointmentOutcomeRecordeController
used by PharmacistView
*/

package Controller;
import Model.Roles.Pharmacist;
import Model.Shared.Medicine;
import Model.Shared.AppointmentOutcome;
import View.PharmacistView;
import Data.Repository.PharmacistRepository;

public class PharmacistController{
    private Pharmacist pharmacist;
    private PharmacistView view;
    private PharmacistRepository repository;

    public PharmacistController(Pharmacist pharmacist, PharmacistView view, PharmacistRepository repository){
        this.pharmacist = pharmacist;
        this.view = view;
        this.repository = repository;
    }
    
    public void requestRestock(){
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Medicine Name to Reqeust Restock:\n ");    	
			String newMedicinename = scanner.nextLine();   

		    System.out.println("Request Sent Succesfully.");
	   }

    }

    public void updatePrescriptionstatus(){

        //change status

        //chage stock level

    }
}



/*in main uml 
contains
+ viewAppointmentoutcome()
+ viewPrescription() 
uses PharmacistController
*/
package View;

import Model.Medicine;
import Data.DataAccess.MedicineDAO;
import java.io.IOException;
import java.util.ArrayList;

public class PharmacistView {

    public void MainMenu() {
        // Display main menu options
    }

    public void viewAppointmentOutcome() {
        // Pharmacist views appointment outcome records to process prescriptions
    }

    public void viewPrescription() {
        // Pharmacist views prescription details
    }

    public void viewInventory() {
        String filename = "Medicine_List.csv";
        try {
            // Read file containing medicine records
            ArrayList<Medicine> medicines = MedicineDAO.readMedicineList(filename);
            for (Medicine med : medicines) {
                System.out.println("Name: " + med.getName());
                System.out.println("Stock: " + med.getStock());
                System.out.println("Low Level Stock Alert: " + med.getLLSA());
            }

        }catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }
}

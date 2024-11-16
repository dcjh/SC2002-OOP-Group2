/*in main uml
contains
+ viewAppointmentoutcome()
+ viewPrescription()
+ viewInventory
uses PharmacistController
*/
package View;
import java.io.*;
import java.util.*;
import Data.Repository.MedicineRepository;
import Controller.PharmacistController;
import java.util.Scanner;

public class PharmacistView {

    private PharmacistController pharmacistController;
    public void PharmacistViewMenu(String pharmacistID){
        private Scanner scanner = new Scanner(System.in);
        public void displayMenu() {
            int option = 0;
            while (option!=5) {
                System.out.println();
                System.out.println("Pharmacist Menu:");
                System.out.println("1. View Appointment Outcome Record ");
                System.out.println("2. Update Prescription Status");
                System.out.println("3. View Medication Inventory");
                System.out.println("4. Submit Replenishment Request");
                System.out.println("5. Logout....");
                System.out.println();
                System.out.println("Select an option:");
                option = scanner.nextInt();
                switch(option) {
                    case 1:viewAppointmentoutcome();
                    case 2:Controller.PharmacistController.updatePrescriptionstatus(null);
                    case 3:viewMedicationinventory();
                    case 4:submitReplenishmentrequest();
                    case 5: break;
                }

            }

        }

        private void viewAppointmentoutcome() {
            System.out.println("Functionality in progress");
        }

        private void viewMedicationinventory() {
            InventoryController inventoryController = new InventoryController();
            InventoryView.displayInventory();
        }


        private void submitReplenishmentrequest() {
            System.out.println("\n--- Submit Replenishment Request ---");
            PharmacistController.submitReplenishmentRequest();
        }
    }

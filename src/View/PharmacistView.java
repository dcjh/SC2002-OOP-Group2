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
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.printf("\n Pharmacist Menu:");
            System.out.printf("1. View Pending Prescriptions\n");
            System.out.printf("2. View Inventory\n");
            System.out.printf("Choose an option");

            int choice = scanner.nextInt();
            scanner.nextLine(); //consume newline

            switch (choice){
                case 1: 
                    viewPendingPrescriptions();
                    break;
                case 2:
                    displayMedicine();
                default:
                    System.out.println("Invalid choice. Please try again.");
                    
            }
        }
    }

    public void viewPendingPrescriptions() {
        // Pharmacist views prescription details
        //not sure how to do this
    }

	public static void displayMedicine() {
        MedicineRepository medicineRepository = new MedicineRepository();
        List<Map<String, String>> medicineDatabase = medicineRepository.load();

        System.out.println("\nDisplay Current Medicine");
        System.out.printf("%-20s %-15s %-20s%n", "Medicine Name", "Initial Stock", "Low Stock Level Alert");
        System.out.println("---------------------------------------------------------------");
        for (Map<String, String> medicine : medicineDatabase) {
            System.out.printf("%-20s %-15s %-20s%n",
                    medicine.get("Medicine Name"),
                    medicine.get("Initial Stock"),
                    medicine.get("Low Stock Level Alert"));
        }
        
	}
}

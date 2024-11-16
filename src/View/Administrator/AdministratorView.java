package View;

import Model.Appointment;
import Model.Gender;
import Model.User;
import Model.UserType;
import java.io.*;
import java.util.*;
import Data.DataAccess.AppointmentDAO;
import Data.DataAccess.InventoryDAO;
import Controller.InventoryController;
import Controller.AdministratorController;


public class AdministratorView implements UserMainView {
    private Scanner scanner = new Scanner(System.in);
    private User user;

    public AdministratorView(User user) {
        this.user = user;
    }

    public void displayMenu() {
        int option = 0;
        while (option!=5) {
            System.out.println();
            System.out.println("Administrator Menu:");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Appointments details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Logout");
            System.out.println();
            System.out.println("Select an option:");
            option = scanner.nextInt();

            switch(option) {
                case 1:viewandmanagerhospitalstaff();
                    break;

                case 2:displayScheduledAppointments();
                    break;

                case 3:viewandmanagemedicationinventory();
                    break;

                case 4:approvereplenishmentrequests();
                    break;

                case 5:System.out.println("Logging out...");
                    break;
                default:System.out.println("Invalid choice. Please try again.");
            }

        }
    }
    private void viewandmanagerhospitalstaff() {
        int option = 0;
        while (option!=5) {
            System.out.println();
            System.out.println("View and Manage Hospital Staff:");
            System.out.println("1. Add Staff Member");
            System.out.println("2. Update Staff Member");
            System.out.println("3. Remove Staff Member");
            System.out.println("4. Display Staff list filtered by role, gender, role");
            System.out.println("5. Return to Main Menu....");
            System.out.println();
            System.out.println("Select an option:");
            option = scanner.nextInt();
            switch(option) {
                case 1:
                    Controller.AdministratorController.addStaffMember();
                    break;
                case 2:
                    Controller.AdministratorController.updateStaffMember();
                    break;

                case 3:
                    Controller.AdministratorController.removeStaffMember();
                    break;

                case 4:
                    AdministratorController.displayStaff();
                    break;
            }
        }


    }


    private void viewandmanagemedicationinventory() {
        Scanner scanner5 = new Scanner(System.in);
        int option5 = 0;
        while (option5!=5) {
            System.out.println();
            System.out.println("View and Manage Medication Inventory:");
            System.out.println("1. Add Medication Record");
            System.out.println("2. Update Stock Level Record");
            System.out.println("3. Remove Medication");
            System.out.println("4. Display Inventory List");
            System.out.println("5. Return to Main Menu....");
            System.out.println();
            System.out.println("Select an option: ");
            option5 = scanner.nextInt();
            switch(option5) {
                case 1:
                    Controller.InventoryController.addMedicine();
                    break;
                case 2:
                    Controller.InventoryController.updateRestockLevel();
                    break;
                case 3:
                    Controller.InventoryController.removeMedicine();
                    break;
                case 4:
                    displayInventory();
                    break;
            }
        }

    }
    private void approvereplenishmentrequests() {
        System.out.println("\n--- Approve/Deny Replenishment Requests ---");
        InventoryController.processReplenishmentRequests();

    }

    private void displayScheduledAppointments() {
        System.out.println("\n--- Viewing Scheduled Appointments ---");
        AdministratorController.displayscheduledAppointmentdetails();
    }




    private void displayInventory() {
        InventoryController inventoryController = new InventoryController();
        InventoryView.displayInventory();
    }

}

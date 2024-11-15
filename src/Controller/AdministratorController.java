package Controller;

import java.util.*;

import Data.DataAccess.AppointmentDAO;
import Data.DataAccess.PatientDAO;
import Data.DataAccess.StaffDAO;
import Data.DataAccess.UserDAO;
import Model.Appointment;
import Model.Gender;
import Model.UserType;

public class AdministratorController {

    private static final StaffDAO staffDAO = new StaffDAO();
    private static final UserDAO userDAO = new UserDAO();
    private static final PatientDAO patientDAO = new PatientDAO();

    public static void addStaffMember() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter staff ID:\n ");
        String newStaffID = scanner.nextLine();
        System.out.print("Enter name:\n");
        String newStaffName = scanner.nextLine();

        UserType newStaffRole = null;
        while (newStaffRole == null) {
            System.out.println("Select role:");
            for (UserType type : UserType.values()) {
                System.out.println(type.ordinal() + 1 + ". " + type);
            }
            System.out.print("Enter choice (1-" + UserType.values().length + "): ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine();

            if (roleChoice >= 1 && roleChoice <= UserType.values().length) {
                newStaffRole = UserType.values()[roleChoice - 1];
            } else {
                System.out.println("Invalid choice. Please select a valid role.");
            }
        }

        Gender newStaffGender = null;
        while (newStaffGender == null) {
            System.out.println("Select gender:");
            for (Gender gender : Gender.values()) {
                System.out.println(gender.ordinal() + 1 + ". " + gender);
            }
            System.out.print("Enter choice (1-" + Gender.values().length + "): ");
            int genderChoice = scanner.nextInt();
            scanner.nextLine();

            if (genderChoice >= 1 && genderChoice <= Gender.values().length) {
                newStaffGender = Gender.values()[genderChoice - 1];
            } else {
                System.out.println("Invalid choice. Please select a valid gender.");
            }
        }

        String dob = null;
        String phoneNumber = null;
        String allergies = null;
        String bloodType = null;
        String email = null;

        if (newStaffRole == UserType.PATIENT) {
            System.out.println("Enter Date of Birth (YYYY-MM-DD):");
            dob = scanner.nextLine();
            System.out.println("Enter Phone Number:");
            phoneNumber = scanner.nextLine();
            System.out.println("Enter Allergies:");
            allergies = scanner.nextLine();
            System.out.println("Enter Blood Type:");
            bloodType = scanner.nextLine();
            System.out.println("Enter your email address:");
            email = scanner.nextLine();
        }

        System.out.print("Enter age:\n");
        int newStaffAge = scanner.nextInt();
        String defaultPassword = "password123";
        String initialLogin = "TRUE";

        if (newStaffRole == UserType.PATIENT) {
            Map<String, String> newPatient = new HashMap<>();
            newPatient.put("Patient ID", newStaffID);
            newPatient.put("Name", newStaffName);
            newPatient.put("Date of Birth", dob);
            newPatient.put("Gender", newStaffGender.toString());
            newPatient.put("Blood Type", bloodType);
            newPatient.put("Allergies", allergies);
            newPatient.put("Phone Number", phoneNumber);
            newPatient.put("Contact Information", email);
            patientDAO.save(newPatient);
        } else {
            Map<String, String> newStaff = new HashMap<>();
            newStaff.put("staffID", newStaffID);
            newStaff.put("name", newStaffName);
            newStaff.put("role", newStaffRole.toString());
            newStaff.put("gender", newStaffGender.toString());
            newStaff.put("age", String.valueOf(newStaffAge));
            staffDAO.save(newStaff);
        }

        Map<String, String> newUser = new HashMap<>();
        newUser.put("hosID", newStaffID);
        newUser.put("password", defaultPassword);
        newUser.put("role", newStaffRole.toString());
        newUser.put("name", newStaffName);
        newUser.put("gender", newStaffGender.toString());
        newUser.put("age", String.valueOf(newStaffAge));
        newUser.put("initialLogin", initialLogin);
        userDAO.save(newUser);

        System.out.println("Staff/User member added successfully.");
    }

    public static void updateStaffMember() {
        Scanner scanner = new Scanner(System.in);
        List<Map<String, String>> staffList = staffDAO.loadAll();

        System.out.print("Enter staff ID to update: ");
        String staffID = scanner.nextLine();
        Map<String, String> staff = staffDAO.find(staffID, null);

        if (staff == null) {
            System.out.println("Staff member with ID " + staffID + " not found.");
            return;
        }

        boolean updating = true;
        while (updating) {
            System.out.println("Select the detail to update:");
            System.out.println("1. Name");
            System.out.println("2. Role");
            System.out.println("3. Gender");
            System.out.println("4. Age");
            System.out.println("5. Done");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    staff.put("name", scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Select new role:");
                    for (UserType type : UserType.values()) {
                        System.out.println((type.ordinal() + 1) + ". " + type);
                    }
                    System.out.print("Enter choice (1-" + UserType.values().length + "): ");
                    int roleChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (roleChoice >= 1 && roleChoice <= UserType.values().length) {
                        UserType newRole = UserType.values()[roleChoice - 1];
                        staff.put("role", newRole.name());
                    } else {
                        System.out.println("Invalid role choice.");
                    }
                    break;
                case 3:
                    System.out.println("Select new gender:");
                    for (Gender gender : Gender.values()) {
                        System.out.println((gender.ordinal() + 1) + ". " + gender);
                    }
                    System.out.print("Enter choice (1-" + Gender.values().length + "): ");
                    int genderChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (genderChoice >= 1 && genderChoice <= Gender.values().length) {
                        Gender newGender = Gender.values()[genderChoice - 1];
                        staff.put("gender", newGender.name());
                    } else {
                        System.out.println("Invalid gender choice.");
                    }
                    break;
                case 4:
                    System.out.print("Enter new age: ");
                    staff.put("age", scanner.nextLine());
                    break;
                case 5:
                    updating = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        staffDAO.save(staff);
        System.out.println("Staff member updated successfully.");
    }

    public static void removeStaffMember() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter staff ID to remove: ");
        String staffID = scanner.nextLine();

        // Attempt deletion in all DAOs
        boolean found = false;

        // Check and delete in StaffDAO
        if (staffDAO.find(staffID, null) != null) {
            staffDAO.delete(staffID, null);
            System.out.println("Staff record with ID " + staffID + " removed successfully from staff records.");
            found = true;
        }

        // Check and delete in UserDAO
        if (userDAO.find(staffID, null) != null) {
            userDAO.delete(staffID, null);
            System.out.println("User account with ID " + staffID + " removed successfully from user records.");
            found = true;
        }

        // Check and delete in PatientDAO
        if (patientDAO.find(staffID, null) != null) {
            patientDAO.delete(staffID, null);
            System.out.println("Patient record with ID " + staffID + " removed successfully from patient records.");
            found = true;
        }

        if (!found) {
            System.out.println("No record with ID " + staffID + " found in any system.");
        }

    }
    public static void displayStaff() {
        StaffDAO staffDAO = new StaffDAO(); // Use the DAO to access staff data
        List<Map<String, String>> staffDatabase = staffDAO.loadAll(); // Load all staff from the DAO
        if (staffDatabase.isEmpty()) {
            System.out.println("No staff records available.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        List<Map<String, String>> filteredList = new ArrayList<>(staffDatabase);
        boolean displayMenu = true;

        while (displayMenu) {
            System.out.println("\nSelect the criteria to filter:");
            System.out.println("1. Role");
            System.out.println("2. Gender");
            System.out.println("3. Age Range");
            System.out.println("4. Display Filtered Results");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1 -> filterByRole(filteredList, scanner);
                case 2 -> filterByGender(filteredList, scanner);
                case 3 -> filterByAgeRange(filteredList, scanner);
                case 4 -> displayFilteredResults(filteredList);
                case 5 -> displayMenu = false;
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void filterByRole(List<Map<String, String>> staffList, Scanner scanner) {
        System.out.println("\nSelect Role:");
        for (UserType role : UserType.values()) {
            System.out.println((role.ordinal() + 1) + ". " + role);
        }
        System.out.print("Enter your choice: ");

        int roleChoice;
        try {
            roleChoice = Integer.parseInt(scanner.nextLine().trim());
            if (roleChoice < 1 || roleChoice > UserType.values().length) {
                System.out.println("Invalid role choice. Please try again.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        UserType selectedRole = UserType.values()[roleChoice - 1];
        staffList.removeIf(staff -> !staff.get("role").equalsIgnoreCase(selectedRole.name()));
        System.out.println("Filtered by role: " + selectedRole);
    }

    private static void filterByGender(List<Map<String, String>> staffList, Scanner scanner) {
        System.out.println("\nSelect Gender:");
        for (Gender gender : Gender.values()) {
            System.out.println((gender.ordinal() + 1) + ". " + gender);
        }
        System.out.print("Enter your choice: ");

        int genderChoice;
        try {
            genderChoice = Integer.parseInt(scanner.nextLine().trim());
            if (genderChoice < 1 || genderChoice > Gender.values().length) {
                System.out.println("Invalid gender choice. Please try again.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        Gender selectedGender = Gender.values()[genderChoice - 1];
        staffList.removeIf(staff -> !staff.get("gender").equalsIgnoreCase(selectedGender.name()));
        System.out.println("Filtered by gender: " + selectedGender);
    }

    private static void filterByAgeRange(List<Map<String, String>> staffList, Scanner scanner) {
        System.out.print("\nEnter Minimum Age: ");
        int minAge;
        try {
            minAge = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        System.out.print("Enter Maximum Age: ");
        int maxAge;
        try {
            maxAge = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        if (minAge > maxAge) {
            System.out.println("Minimum age cannot be greater than maximum age.");
            return;
        }

        staffList.removeIf(staff -> {
            try {
                int age = Integer.parseInt(staff.get("age"));
                return age < minAge || age > maxAge;
            } catch (NumberFormatException e) {
                return true; // Skip invalid age values
            }
        });
        System.out.println("Filtered by age range: " + minAge + " to " + maxAge);
    }

    private static void displayFilteredResults(List<Map<String, String>> staffList) {
        if (staffList.isEmpty()) {
            System.out.println("No staff members match the current filter criteria.");
        } else {
            System.out.println("\nFiltered Staff List:");
            System.out.printf("%-15s %-20s %-20s %-10s %-5s%n", "Staff ID", "Name", "Role", "Gender", "Age");
            System.out.println("----------------------------------------------------------------------");
            for (Map<String, String> staff : staffList) {
                System.out.printf("%-15s %-20s %-20s %-10s %-5s%n",
                        staff.get("staffID"),
                        staff.get("name"),
                        staff.get("role"),
                        staff.get("gender"),
                        staff.get("age"));
            }
        }
    }
    
	public static void displayscheduledAppointmentdetails() {
	    AppointmentDAO appointmentDAO = new AppointmentDAO();
	    List<Appointment> appointments = appointmentDAO.loadAll();
	    if (appointments.isEmpty()) {
	        System.out.println("No scheduled appointments available.");
	        return;
	    }
	    System.out.println("\n--- Viewing Scheduled Appointments ---");
	    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n",
	            "Appointment ID", "Doctor ID", "Patient ID", "Status", "Date", "Time");
	    System.out.println("--------------------------------------------------------------------------------");

	    for (Appointment appointment : appointments) {
	        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n",
	                appointment.getAppointmentID(),
	                appointment.getDocID(),
	                appointment.getPatientID(),
	                appointment.getStatus(),
	                appointment.getDate(),
	                appointment.getTime());
	    }
	
    }
    
}

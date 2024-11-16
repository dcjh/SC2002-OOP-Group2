package View.Appointments;

import Controller.AppointmentOutcomeController;
import Model.Shared.Appointment;
import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentOutcomeView {
    private AppointmentOutcomeController appointmentOutcomeController;

    public AppointmentOutcomeView(AppointmentOutcomeController appointmentOutcomeController) {
        this.appointmentOutcomeController = appointmentOutcomeController;
    }

    public void printAppointmentOutcome(String AppointmentOutcomeID, String doctorID, String patientID, String appointmentID, String date, String time, String typeOfService, ArrayList<PrescribedMedication> prescribedMedications, String consultationNotes) {
    System.out.printf("%-25s : %s%n", "Appointment Outcome ID", AppointmentOutcomeID);
    System.out.printf("%-25s : %s%n", "Doctor ID", doctorID);
    System.out.printf("%-25s : %s%n", "Patient ID", patientID);
    System.out.printf("%-25s : %s%n", "Appointment ID", appointmentID);
    System.out.printf("%-25s : %s%n", "Date", date);
    System.out.printf("%-25s : %s%n", "Time", time);
    System.out.printf("%-25s : %s%n", "Type of Service", typeOfService);
    System.out.printf("%-25s : %s%n", "Consultation Notes", consultationNotes);
    
    /*
    System.out.printf("%-20s : ", "Prescribed Medications");
    if (prescribedMedications.isEmpty()) {
        System.out.println("None");
    } else {
        System.out.println();
        for (PrescribedMedication medication : prescribedMedications) {
            System.out.printf("  - %s%n", medication.toString());
        }
    }
    */

    System.out.printf("%-25s : %n", "Prescribed Medications");
    if (prescribedMedications.isEmpty()) {
        System.out.println("None");
    } else {
        System.out.printf("%-20s %-15s %-10s%n", "Medicine Name", "Quantity", "Status");
        System.out.println("--------------------------------------------------");
        for (PrescribedMedication medication : prescribedMedications) {
            System.out.printf("%-20s %-15d %-10s%n",
                    medication.getMedicineName(), // Assuming Medicine has getMedicineName()
                    medication.getQuantity(),     // Assuming Medicine has getQuantity()
                    medication.getStatus());
        }
    }
}
    public void menuDoctor(String doctorId){
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Choose your option:");
        System.out.println("1. View all your Appointment Outcome");
        System.out.println("2. Add a new Appointment Outcome");
        System.out.println("3. Update the consultation note of an existing Appointment Outcome");
        
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                List<AppointmentOutcome> outcomes = appointmentOutcomeController.getAppointmentOutcomeByDoctorID(doctorId);
                System.out.println(" ");
                System.out.println("All the appointment outcomes for the Doctor ID " + doctorId + " : ");
                System.out.println(" ");
                for (AppointmentOutcome o : outcomes){
                    appointmentOutcomeController.printAppointmentOutcome(o);
                    System.out.println(" ");
                }
                break;
            case 2:
                sc.nextLine();
                System.out.println(" ");
                appointmentOutcomeController.viewAppointmentsByDoctorID(doctorId);
                System.out.println("Select which appoinment would you like to record? Input the AppointmentID : ");
                String aptID = sc.nextLine();
                Appointment appointment = appointmentOutcomeController.getAppointment(aptID);
                String patientID = appointment.getPatientID();
                String date = appointment.getDate();
                String time = appointment.getTime();
                System.out.println("Input the type of service : ");
                String typeOfService = sc.nextLine();
                ArrayList<PrescribedMedication> medications = new ArrayList<>();
                System.out.println("Input the prescribed medication and its quntity ");
                System.out.println("------------------------------------------------");
                while (true) {
                    System.out.println("Enter medicine name (type 'n' to stop):");
                    String medicineName = sc.nextLine(); // Read the input
                    if (medicineName.equalsIgnoreCase("n")) { // Check if the input is "n" (case-insensitive)
                        break; // Exit the loop
                    }
                    System.out.println("Enter quanity of "+ medicineName + " : ");
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    medications.add(new PrescribedMedication(medicineName, quantity));
                }
                System.out.println("Input consultation notes :");
                String consultationNotes = sc.nextLine();
                appointmentOutcomeController.createAppointmentOutcome(date, time, typeOfService, medications, consultationNotes, doctorId, patientID, aptID);
                System.out.println("Appointment Outcome created successfully.");
                break;
            case 3:
                sc.nextLine();
                List<AppointmentOutcome> outcomesPast = appointmentOutcomeController.getAppointmentOutcomeByDoctorID(doctorId);
                System.out.println(" ");
                System.out.println("All the appointment outcomes for the Doctor ID " + doctorId + " : ");
                System.out.println(" ");
            for (AppointmentOutcome o : outcomesPast){
                appointmentOutcomeController.printAppointmentOutcome(o);
                System.out.println(" ");
            }

            System.out.println("Select which appoinment outcome would you like to update? Input the Appointment Outcome ID : ");
            String aptOutcomeID = sc.nextLine();

            System.out.println("Input your new consultation notes : ");
            String newConsultationNotes = sc.nextLine();
            appointmentOutcomeController.setConsultationNotes(aptOutcomeID, newConsultationNotes);
            System.out.println("Consultation Notes is updated successfully for Appointment Outcome " + aptOutcomeID + ".");
                break;
            default:
                System.out.println("Invalid input.");;
        }
    }

}
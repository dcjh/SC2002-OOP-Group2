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

    /**
     * Constructor for AppointmentOutcomeView.
     * 
     * @param appointmentOutcomeController The controller for managing appointment outcomes.
     */
    public AppointmentOutcomeView(AppointmentOutcomeController appointmentOutcomeController) {
        this.appointmentOutcomeController = appointmentOutcomeController;
    }

    /**
     * Prints the details of an appointment outcome.
     * 
     * @param appointmentOutcomeID The ID of the appointment outcome.
     * @param doctorID The ID of the doctor.
     * @param patientID The ID of the patient.
     * @param appointmentID The ID of the appointment.
     * @param date The date of the appointment.
     * @param time The time of the appointment.
     * @param typeOfService The type of service provided.
     * @param prescribedMedications The list of prescribed medications.
     * @param consultationNotes The consultation notes.
     */
    public void printAppointmentOutcome(String appointmentOutcomeID, String doctorID, String patientID, String appointmentID, String date, String time, String typeOfService, ArrayList<PrescribedMedication> prescribedMedications, String consultationNotes) {
        System.out.printf("%-25s : %s%n", "Appointment Outcome ID", appointmentOutcomeID);
        System.out.printf("%-25s : %s%n", "Doctor ID", doctorID);
        System.out.printf("%-25s : %s%n", "Patient ID", patientID);
        System.out.printf("%-25s : %s%n", "Appointment ID", appointmentID);
        System.out.printf("%-25s : %s%n", "Date", date);
        System.out.printf("%-25s : %s%n", "Time", time);
        System.out.printf("%-25s : %s%n", "Type of Service", typeOfService);
        System.out.printf("%-25s : %s%n", "Consultation Notes", consultationNotes);

        System.out.printf("%-25s : %n", "Prescribed Medications");
        if (prescribedMedications.isEmpty()) {
            System.out.println("None");
        } else {
            System.out.printf("%-20s %-15s %-10s%n", "Medicine Name", "Quantity", "Status");
            System.out.println("--------------------------------------------------");
            for (PrescribedMedication medication : prescribedMedications) {
                System.out.printf("%-20s %-15d %-10s%n",
                        medication.getMedicineName(),
                        medication.getQuantity(),
                        medication.getStatus());
            }
        }
    }

    /**
     * Displays the menu for the doctor to manage appointment outcomes.
     * 
     * @param doctorId The ID of the doctor.
     */
    public void menuDoctor(String doctorId) {
        Scanner sc = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("Choose your option:");
            System.out.println("1. View all your Appointment Outcomes");
            System.out.println("2. Add a new Appointment Outcome");
            System.out.println("3. Return back to Menu");

            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    List<AppointmentOutcome> outcomes = appointmentOutcomeController.getAppointmentOutcomeByDoctorID(doctorId);
                    if (outcomes.isEmpty()) {
                        System.out.println("You have no appointment outcome records.");
                        return;
                    }
                    System.out.println(" ");
                    System.out.println("All the appointment outcomes for Doctor ID " + doctorId + " : ");
                    System.out.println(" ");
                    for (AppointmentOutcome o : outcomes) {
                        appointmentOutcomeController.printAppointmentOutcome(o);
                        System.out.println(" ");
                    }
                    return;
                case "2":
                    System.out.println(" ");
                    appointmentOutcomeController.viewAppointmentsByDoctorID(doctorId);
                    int lengthOfApprovedOutcome = appointmentOutcomeController.getApprovedAppointmentsByDoctorID(doctorId).size();
                    if (lengthOfApprovedOutcome == 0) {
                        return;
                    }
                    System.out.println("Select which appointment you would like to record. Input the Appointment ID: ");
                    String aptID = sc.nextLine();
                    Appointment appointment = appointmentOutcomeController.getAppointment(aptID);
                    if (appointment == null) {
                        System.out.println("Appointment with Appointment ID " + aptID + " is not found.");
                        break;
                    }
                    String patientID = appointment.getPatientID();
                    String date = appointment.getDate();
                    String time = appointment.getTime();
                    System.out.println("Input the type of service: ");
                    String typeOfService = sc.nextLine();
                    ArrayList<PrescribedMedication> medications = new ArrayList<>();
                    System.out.println("Input the prescribed medication and its quantity");
                    System.out.println("------------------------------------------------");
                    while (true) {
                        System.out.println("Enter medicine name (type 'n' to stop):");
                        String medicineName = sc.nextLine();
                        if (medicineName.equalsIgnoreCase("n")) {
                            break;
                        }

                        int quantity = 0;

                        while (true) {
                            try {
                                System.out.println("Enter quantity of " + medicineName + " : ");
                                quantity = sc.nextInt();
                                sc.nextLine();
                                break;
                            } catch (java.util.InputMismatchException e) {
                                sc.nextLine();
                                System.out.println("Invalid input. Please enter a valid number.");
                            }
                        }
                        medications.add(new PrescribedMedication(medicineName, quantity));
                    }
                    System.out.println("Input consultation notes:");
                    String consultationNotes = sc.nextLine();
                    appointmentOutcomeController.createAppointmentOutcome(date, time, typeOfService, medications, consultationNotes, doctorId, patientID, aptID);
                    System.out.println("Appointment Outcome created successfully.");
                    return;
                case "3":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}

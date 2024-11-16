package View.Doctor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import Controller.DoctorController;
import Controller.MedicalRecordController;
import Model.Shared.MedicalRecord;

public class DoctorMedicalRecordView {
 
        private MedicalRecordController medicalRecordController;

    public DoctorMedicalRecordView (MedicalRecordController medicalRecordController) {
        this.medicalRecordController = medicalRecordController;
    }

    public void menu(String doctorId) {
        Scanner scanner = new Scanner(System.in);
        String linebr = "----------------------------------------------";

        if (medicalRecordController.getPendingAppointmentsByDoctorID(doctorId).isEmpty()) {
            System.out.println("No pending appointments to approve.");
            return;
        }

        while(true){
            System.out.println(linebr);
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Return to main menu");
            System.out.println(linebr);
            System.out.print("Enter Choice:");
            int proceed;

            try {
                proceed = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (proceed) {
                case 1:
                    viewPatient(patients);
                    break;
                case 2:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again...");
            }
        }

    }

    private void printAllPatients(List<M> appointments) {
        String border = "+-------------+-----------+------------+----------+------------+--------+";
        String header = "| Appointment | Doctor ID | Patient ID |  Status  |    Date    |  Time  |";
    
        System.out.println(border);
        System.out.println(header);
        System.out.println(border);
    
        for (Appointment a : appointments) {
            System.out.printf("| %-11s | %-9s | %-10s | %-8s | %-10s | %-6s |%n",
                    a.getAppointmentID(),
                    a.getDocID(),
                    a.getPatientID(),
                    a.getStatus(),
                    a.getDate(),
                    a.getTime());
            System.out.println(border);
        }
    }

    public void approve(Scanner scanner, String doctorId) {
        List<Appointment> pendingAppointments = appointmentController.getPendingAppointmentsByDoctorID(doctorId);
        String linebr = "----------------------------------------------";
        Appointment selectedAppointment = null;

        printCurrentAppointments(pendingAppointments);
        System.out.println("Please choose an appointment to approve (Enter Appointment ID):");
        System.out.println(linebr);

        do {
            String appointmentID = scanner.nextLine();

            for (Appointment a : pendingAppointments) {
                if (a.getAppointmentID().equals(appointmentID)) {
                    selectedAppointment = a;
                    break;
                }
            }
            System.out.println("Invalid Appointment ID. Please try again.");
        } while (selectedAppointment == null);

        appointmentController.updateAppointmentStatus(selectedAppointment.getAppointmentID(), "approved");
        appointmentController.updateAppointmentSchedule(selectedAppointment.getAppointmentID(), doctorId, selectedAppointment.getDate(), true);
        System.out.println("Appointment ID " + selectedAppointment.getAppointmentID() + " has been approved.");
    }

    public void reject(Scanner scanner, String doctorId) {
        List<Appointment> pendingAppointments = appointmentController.getPendingAppointmentsByDoctorID(doctorId);
        String linebr = "----------------------------------------------";
        Appointment selectedAppointment = null;

        printCurrentAppointments(pendingAppointments);
        System.out.println("Please choose an appointment to reject (Enter Appointment ID):");
        System.out.println(linebr);

        do {
            String appointmentID = scanner.nextLine();

            for (Appointment a : pendingAppointments) {
                if (a.getAppointmentID().equals(appointmentID)) {
                    selectedAppointment = a;
                    break;
                }
            }
            System.out.println("Invalid Appointment ID. Please try again.");
        } while (selectedAppointment == null);

        appointmentController.updateAppointmentStatus(selectedAppointment.getAppointmentID(), "cancelled");
        appointmentController.updateAppointmentSchedule(selectedAppointment.getAppointmentID(), doctorId, selectedAppointment.getDate(), false);
        System.out.println("Appointment ID " + selectedAppointment.getAppointmentID() + " has been rejected.");
    }

}

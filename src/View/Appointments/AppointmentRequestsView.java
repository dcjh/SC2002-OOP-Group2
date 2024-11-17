package View.Appointments;

import java.util.List;
import java.util.Scanner;

import Model.Shared.Appointment;
import Controller.AppointmentController;

public class AppointmentRequestsView {
    
    private AppointmentController appointmentController;

    public AppointmentRequestsView (AppointmentController appointmentController) {
        this.appointmentController = appointmentController;
    }

    public void menu(String doctorId) {
        Scanner scanner = new Scanner(System.in);
        String linebr = "----------------------------------------------";

        if (appointmentController.getPendingAppointmentsByDoctorID(doctorId).isEmpty()) {
            System.out.println("No pending appointments to approve.");
            return;
        }

        while(true){
            System.out.println(linebr);
            System.out.println("1. Approve Appointment");
            System.out.println("2. Reject Appointment");
            System.out.println("3. Return to main menu");
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
                    approve(scanner,doctorId);
                    break;
                case 2:
                    reject(scanner,doctorId);
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again...");
            }
        }

    }

    private void printCurrentAppointments(List<Appointment> appointments) {
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
        if (pendingAppointments.isEmpty())  {
            System.out.println("No pending appointments to approve.");
            return;
        }

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
            if (selectedAppointment == null) System.out.println("Invalid Appointment ID. Please try again.");
        } while (selectedAppointment == null);

        appointmentController.updateAppointmentSchedule(selectedAppointment.getAppointmentID(), doctorId, selectedAppointment.getDate(), selectedAppointment.getTime(), true);
        System.out.println("Appointment ID " + selectedAppointment.getAppointmentID() + " has been approved.");
    }

    public void reject(Scanner scanner, String doctorId) {
        List<Appointment> pendingAppointments = appointmentController.getPendingAppointmentsByDoctorID(doctorId);
        String linebr = "----------------------------------------------";
        Appointment selectedAppointment = null;
        if (pendingAppointments.isEmpty()) {
            System.out.println("No pending appointments to approve.");
            return;
        }

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
            if (selectedAppointment == null) System.out.println("Invalid Appointment ID. Please try again.");
        } while (selectedAppointment == null);

        appointmentController.updateAppointmentSchedule(selectedAppointment.getAppointmentID(), doctorId, selectedAppointment.getDate(), selectedAppointment.getTime(), false);
        System.out.println("Appointment ID " + selectedAppointment.getAppointmentID() + " has been rejected.");
    }

}

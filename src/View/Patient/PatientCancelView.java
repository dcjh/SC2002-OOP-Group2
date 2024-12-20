package View.Patient;

import java.util.List;
import java.util.Scanner;

import Model.Shared.Appointment;
import Controller.ScheduleController;

/**
 * View class for allowing patients to cancel their scheduled appointments.
 */
public class PatientCancelView {
    private ScheduleController scheduleController;

    /**
     * Constructor for PatientCancelView.
     * 
     * @param scheduleController The controller for managing appointment schedules.
     */
    public PatientCancelView(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    /**
     * Displays the menu for canceling appointments.
     * 
     * @param aList A list of appointments available to the patient.
     * @param patientId The ID of the patient requesting cancellation.
     */
    public void menu(List<Appointment> aList, String patientId) {
        String br = "----------------------------------------------";
        Scanner scanner = new Scanner(System.in);

        if (aList.isEmpty()) {
            System.out.println("No appointments found to cancel.");
            return;
        }

        System.out.println(br);
        System.out.println("Your Appointments:");
        System.out.println(br);

        // Print appointment details
        printAppointments(aList);

        System.out.println(br);

        while (true) {
            System.out.print("Enter the Appointment ID to cancel or type 'exit' to return: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Returning to the main menu...");
                break;
            }

            boolean appointmentFound = false;

            for (Appointment appointment : aList) {
                if (appointment.getAppointmentID().equals(input)) {
                    // Cancel the appointment using the ScheduleController
                    scheduleController.cancelAppointment(appointment.getAppointmentID(), appointment.getDocID(), appointment.getDate(), appointment.getTime());
                    System.out.println("Appointment ID " + appointment.getAppointmentID() + " has been canceled.");
                    appointmentFound = true;
                    break;
                }
            }

            if (!appointmentFound) {
                System.out.println("Invalid Appointment ID. Please try again.");
            } else {
                break;
            }
        }
    }

    /**
     * Prints the list of current appointments.
     * 
     * @param aList A list of appointments available to the patient.
     */
    private void printAppointments(List<Appointment> aList) {
        String border = "+-------------+------------+----------+------------+----------+";
        String header = "| Appointment |    Date    |   Time   |   Doctor   |  Status  |";

        System.out.println(border);
        System.out.println(header);
        System.out.println(border);

        for (Appointment a : aList) {
            System.out.printf("| %-11s | %-10s | %-8s | %-10s | %-6s |%n",
                a.getAppointmentID(),
                a.getDate(),
                a.getTime(),
                a.getDocID(),
                a.getStatus());
            System.out.println(border);
        }
    }
}

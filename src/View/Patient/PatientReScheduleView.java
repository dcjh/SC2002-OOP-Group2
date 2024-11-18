package View.Patient;

import Controller.ScheduleController;

import java.util.List;
import java.util.Scanner;

import Model.Shared.Appointment;

/**
 * This view class allows patients to reschedule their confirmed appointments.
 */
public class PatientReScheduleView {

    private ScheduleController scheduleController;

    /**
     * Constructor for PatientReScheduleView.
     *
     * @param scheduleController The controller used to manage and reschedule appointments.
     */
    public PatientReScheduleView(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    /**
     * Displays the menu for rescheduling confirmed appointments.
     *
     * @param confirmedAppointments A list of confirmed appointments for the patient.
     * @param patientId             The ID of the patient requesting the reschedule.
     */
    public void menu(List<Appointment> confirmedAppointments, String patientId) {
        Scanner scanner = new Scanner(System.in);
        String linebr = "----------------------------------------------";

        if (confirmedAppointments.isEmpty()) {
            System.out.println(linebr);
            System.out.println("You have no confirmed appointments to reschedule.");
            System.out.println(linebr);
            return;
        }

        // Display the appointments
        System.out.println("Existing Appointments.");
        printAppointments(confirmedAppointments);

        // Select an appointment to reschedule
        Appointment selectedAppointment = null;
        while (selectedAppointment == null) {
            System.out.print("Enter the Appointment ID to reschedule: ");
            String appointmentId = scanner.nextLine();
            for (Appointment appointment : confirmedAppointments) {
                if (appointment.getAppointmentID().equalsIgnoreCase(appointmentId)) {
                    selectedAppointment = appointment;
                    break;
                }
            }
            if (selectedAppointment == null) {
                System.out.println("Invalid Appointment ID. Please try again.");
            }
        }

        // Print doctor availability
        System.out.println(linebr);
        System.out.println("Current Doctor Availability.");
        scheduleController.patientScheduleView();

        // Input new date and time
        System.out.println(linebr);
        System.out.println("Enter new date and time for the appointment.");
        String newDate;
        String newTime;
        while (true) {
            System.out.print("Enter new Date (dd-MM-yyyy): ");
            newDate = scanner.nextLine();
            System.out.print("Enter new Time (HH:mm): ");
            newTime = scanner.nextLine();

            if (isValidDate(newDate) && isValidTime(newTime)) {
                break;
            } else {
                System.out.println("Invalid date or time format. Please try again.");
            }
        }

        // Confirm rescheduling
        System.out.println(linebr);
        System.out.println("Appointment Reschedule Preview:");
        System.out.printf("Appointment ID: %s%n", selectedAppointment.getAppointmentID());
        System.out.printf("Current Date  : %s%n", selectedAppointment.getDate());
        System.out.printf("Current Time  : %s%n", selectedAppointment.getTime());
        System.out.printf("New Date      : %s%n", newDate);
        System.out.printf("New Time      : %s%n", newTime);
        System.out.println(linebr);

        System.out.print("Do you want to proceed with rescheduling? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("yes")) {
            scheduleController.rescheduleAppointment(selectedAppointment.getAppointmentID(), selectedAppointment.getDocID(), selectedAppointment.getDate(), selectedAppointment.getTime(), newDate, newTime);
            System.out.println("Appointment has been rescheduled successfully!");
        } else {
            System.out.println("Rescheduling canceled.");
        }
    }

    /**
     * Prints the list of current appointments.
     *
     * @param appointments A list of appointments to be displayed.
     */
    private void printAppointments(List<Appointment> appointments) {
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

    /**
     * Validates the format of the given date.
     *
     * @param date The date string to be validated.
     * @return true if the date format is valid, false otherwise.
     */
    private boolean isValidDate(String date) {
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");
            java.time.LocalDate.parse(date, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates the format of the given time.
     *
     * @param time The time string to be validated.
     * @return true if the time format is valid, false otherwise.
     */
    private boolean isValidTime(String time) {
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("HH:mm");
            java.time.LocalTime.parse(time, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

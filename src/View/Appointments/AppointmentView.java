package View.Appointments;

/**
 * AppointmentView class provides a method to display the details of an appointment.
 */
public class AppointmentView {
    
    /**
     * Prints the details of an appointment.
     *
     * @param appointmentID The unique identifier of the appointment.
     * @param docID The ID of the doctor associated with the appointment.
     * @param patientID The ID of the patient associated with the appointment.
     * @param status The current status of the appointment (e.g., pending, approved, completed).
     * @param time The time of the appointment.
     * @param date The date of the appointment.
     */
    public void printAppointment(String appointmentID, String docID, String patientID, String status, String time, String date) {
        System.out.printf("%-15s : %s%n", "Appointment ID", appointmentID);
        System.out.printf("%-15s : %s%n", "Doctor ID", docID);
        System.out.printf("%-15s : %s%n", "Patient ID", patientID);
        System.out.printf("%-15s : %s%n", "Status", status);
        System.out.printf("%-15s : %s%n", "Date", date);
        System.out.printf("%-15s : %s%n", "Time", time);
    }
}

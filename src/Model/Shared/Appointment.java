package Model.Shared;

/**
 * Represents an appointment between a doctor and a patient in the hospital system.
 */
public class Appointment {
    private static int idCounter = 1; 
    private String appointmentID;  
    private String docID;
    private String patientID;
    private String time;
    private String date;
    private String status;

    /**
     * Creates an appointment with the specified doctor ID, patient ID, time, date, appointment ID, and status.
     * 
     * @param docID the ID of the doctor.
     * @param patientID the ID of the patient.
     * @param time the time of the appointment.
     * @param date the date of the appointment.
     * @param appointmentID the unique ID for the appointment.
     * @param status the status of the appointment.
     */
    public Appointment(String docID, String patientID, String time, String date, String appointmentID, String status) {
        this.appointmentID = appointmentID;
        this.docID = docID;
        this.patientID = patientID;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    /**
     * Creates an appointment with the specified doctor ID, patient ID, time, date, and appointment ID.
     * The status of the appointment is set to "pending" by default.
     * 
     * @param docID the ID of the doctor.
     * @param patientID the ID of the patient.
     * @param time the time of the appointment.
     * @param date the date of the appointment.
     * @param appointmentID the unique ID for the appointment.
     */
    public Appointment(String docID, String patientID, String time, String date, String appointmentID) {
        this(docID, patientID, time, date, appointmentID, "pending");
    }

    // Getter methods

    /**
     * Gets the appointment ID.
     * 
     * @return the appointment ID.
     */
    public String getAppointmentID() {
        return this.appointmentID;
    }

    /**
     * Gets the patient ID.
     * 
     * @return the patient ID.
     */
    public String getPatientID() {
        return this.patientID;
    }

    /**
     * Gets the doctor ID.
     * 
     * @return the doctor ID.
     */
    public String getDocID() {
        return this.docID;
    }

    /**
     * Gets the status of the appointment.
     * 
     * @return the appointment status.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Gets the date of the appointment.
     * 
     * @return the appointment date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Gets the time of the appointment.
     * 
     * @return the appointment time.
     */
    public String getTime() {
        return this.time;
    }

    // Setter methods

    /**
     * Sets the status of the appointment.
     * Possible statuses are: "pending", "cancelled", "approved", "completed".
     * 
     * @param newStatus the new status of the appointment.
     */
    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    /**
     * Sets the date of the appointment.
     * 
     * @param newDate the new date for the appointment.
     */
    public void setDate(String newDate) {
        this.date = newDate;
    }

    /**
     * Sets the time of the appointment.
     * 
     * @param newTime the new time for the appointment.
     */
    public void setTime(String newTime) {
        this.time = newTime;
    }

    // Note: There are no setter methods for patientID and doctorID because they cannot be modified after creation.
}

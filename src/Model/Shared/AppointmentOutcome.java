package Model.Shared;

import java.util.ArrayList;

/**
 * Represents the outcome of a completed appointment, including prescribed medications,
 * consultation notes, and the status of the prescribed medications.
 * 
 * <p>
 * Note: An appointment outcome is only created after an appointment is marked as completed.
 * </p>
 */
public class AppointmentOutcome {
    private static int idCounter = 1; 
    private String appointmentOutcomeID;
    private String doctorID;
    private String patientID;  
    private String appointmentID;  
    private String date;
    private String time;
    private String typeOfService;
    private ArrayList<PrescribedMedication> prescribedMedications;
    private String consultationNotes;

    // there are 2 possible values for status (status of medicine) ["pending", "dispensed"], 
    // if there is an event where the medicine is not in stock yet, status will be pending until 
    // stock is replenished and pharmacist is able to dispense.
    // the default value for medicine status is "pending" when Appointment Outcome is first created,
    // then it is changed to "dispensed" after the medication is given by the pharmacist.

    /**
     * Creates an appointment outcome with the specified details.
     * 
     * @param date               the date of the appointment.
     * @param time               the time of the appointment.
     * @param typeOfService      the type of service provided during the appointment.
     * @param prescribedMedications the list of prescribed medications.
     * @param consultationNotes  the notes taken during the consultation.
     * @param doctorID           the ID of the doctor.
     * @param patientID          the ID of the patient.
     * @param appointmentID      the ID of the appointment.
     * @param appointmentOutcomeID the unique ID for the appointment outcome.
     */
    public AppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> prescribedMedications, String consultationNotes, String doctorID, String patientID, String appointmentID, String appointmentOutcomeID){
        this.appointmentOutcomeID = appointmentOutcomeID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.appointmentID = appointmentID;
        this.date = date;
        this.time = time;
        this.typeOfService = typeOfService;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
    }

    /**
     * Creates an appointment outcome with a generated outcome ID.
     * 
     * @param date               the date of the appointment.
     * @param time               the time of the appointment.
     * @param typeOfService      the type of service provided during the appointment.
     * @param prescribedMedications the list of prescribed medications.
     * @param consultationNotes  the notes taken during the consultation.
     * @param doctorID           the ID of the doctor.
     * @param patientID          the ID of the patient.
     * @param appointmentID      the ID of the appointment.
     */
    public AppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> prescribedMedications, String consultationNotes, String doctorID, String patientID, String appointmentID){
        this(date, time, typeOfService, prescribedMedications, consultationNotes, doctorID, patientID, appointmentID, generateFormattedId());
    }
        
    /**
     * Generates a formatted appointment outcome ID with prefix "AO" and leading zeroes.
     * 
     * @return the formatted appointment outcome ID.
     */
    private static String generateFormattedId() {
        return String.format("AO%04d", idCounter++); // AO + zero-padded 4-digit ID
    }

    // Getter methods

    /**
     * Gets the appointment outcome ID.
     * 
     * @return the appointment outcome ID.
     */
    public String getAppointmentOutcomeID(){
        return this.appointmentOutcomeID;
    }

    /**
     * Gets the doctor ID.
     * 
     * @return the doctor ID.
     */
    public String getDoctorID(){
        return this.doctorID;
    }

    /**
     * Gets the patient ID.
     * 
     * @return the patient ID.
     */
    public String getPatientID(){
        return this.patientID;
    }

    /**
     * Gets the appointment ID.
     * 
     * @return the appointment ID.
     */
    public String getAppointmentID(){
        return this.appointmentID;
    }

    /**
     * Gets the date of the appointment.
     * 
     * @return the date of the appointment.
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Gets the time of the appointment.
     * 
     * @return the time of the appointment.
     */
    public String getTime(){
        return this.time;
    }

    /**
     * Gets the type of service provided during the appointment.
     * 
     * @return the type of service.
     */
    public String getTypeOfService(){
        return this.typeOfService;
    }

    /**
     * Gets the list of prescribed medications.
     * 
     * @return the list of prescribed medications.
     */
    public ArrayList<PrescribedMedication> getPrescribedMedications(){
        return this.prescribedMedications;
    }

    /**
     * Gets the consultation notes.
     * 
     * @return the consultation notes.
     */
    public String getConsultationNotes(){
        return this.consultationNotes;
    }

    // Setter methods 

    /**
     * Sets the status of a prescribed medication to "dispensed" after it has been dispensed by the pharmacist.
     * 
     * @param medicineName the name of the medication to be updated.
     */
    public void setStatusDispensed(String medicineName){
        for (PrescribedMedication medication : prescribedMedications) {
            if (medication.getMedicineName().equals(medicineName)) {
                medication.setStatusDispensed();
                return;
            }
        }
    }

    /**
     * Sets new consultation notes.
     * 
     * @param newConsultationNotes the updated consultation notes.
     */
    public void setConsultationNotes(String newConsultationNotes){
        this.consultationNotes = newConsultationNotes;
    }
}

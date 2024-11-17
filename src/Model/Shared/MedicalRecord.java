package Model.Shared;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a medical record of a patient, which includes personal information, appointment outcomes,
 * and past diagnoses and treatments.
 */
public class MedicalRecord {

    private String patientID;
    private String name;
    private String dob;
    private String gender;
    private String phoneNumber;
    private String email;
    private String bloodType;
    private String allergies;
    private List<AppointmentOutcome> appointmentOutcome;
    private List<PastDiagnosesAndTreatments> pastDiagnosesAndTreatments;

    /**
     * Creates a new MedicalRecord for a patient.
     *
     * @param patientID       The unique ID of the patient.
     * @param name            The name of the patient.
     * @param dob             The date of birth of the patient.
     * @param gender          The gender of the patient.
     * @param phoneNumber     The phone number of the patient.
     * @param email           The email address of the patient.
     * @param bloodType       The blood type of the patient.
     * @param allergies       Any allergies the patient has.
     */
    public MedicalRecord(String patientID, String name, String dob, String gender, String phoneNumber, String email, String bloodType, String allergies) {
        this.patientID = patientID;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.appointmentOutcome = new ArrayList<>();
        this.pastDiagnosesAndTreatments = new ArrayList<>();
    }

    /**
     * Gets the patient ID.
     *
     * @return The unique ID of the patient.
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Sets the patient ID.
     *
     * @param patientID The new ID of the patient.
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * Gets the name of the patient.
     *
     * @return The name of the patient.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the patient.
     *
     * @param name The new name of the patient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date of birth of the patient.
     *
     * @return The date of birth.
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the patient.
     *
     * @param dob The new date of birth.
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Gets the gender of the patient.
     *
     * @return The gender of the patient.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the patient.
     *
     * @param gender The new gender of the patient.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the phone number of the patient.
     *
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the patient.
     *
     * @param phoneNumber The new phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the email address of the patient.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the patient.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the blood type of the patient.
     *
     * @return The blood type.
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * Sets the blood type of the patient.
     *
     * @param bloodType The new blood type.
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Gets any allergies the patient has.
     *
     * @return A description of the patient's allergies.
     */
    public String getAllergies() {
        return allergies;
    }

    /**
     * Sets the allergies of the patient.
     *
     * @param allergies The new list of allergies.
     */
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    /**
     * Gets the list of appointment outcomes for the patient.
     *
     * @return The list of appointment outcomes.
     */
    public List<AppointmentOutcome> getAppointmentOutcome() {
        return appointmentOutcome;
    }

    /**
     * Sets the list of appointment outcomes for the patient.
     *
     * @param appointmentOutcome The new list of appointment outcomes.
     */
    public void setAppointmentOutcome(List<AppointmentOutcome> appointmentOutcome) {
        this.appointmentOutcome = appointmentOutcome;
    }

    /**
     * Gets the list of past diagnoses and treatments for the patient.
     *
     * @return The list of past diagnoses and treatments.
     */
    public List<PastDiagnosesAndTreatments> getPastDiagnosesAndTreatments() {
        return pastDiagnosesAndTreatments;
    }

    /**
     * Sets the list of past diagnoses and treatments for the patient.
     *
     * @param pastDiagnosesAndTreatments The new list of past diagnoses and treatments.
     */
    public void setPastDiagnosesAndTreatments(List<PastDiagnosesAndTreatments> pastDiagnosesAndTreatments) {
        this.pastDiagnosesAndTreatments = pastDiagnosesAndTreatments;
    }
}

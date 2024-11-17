package Model.Shared;

/**
 * Represents a past diagnosis and treatment for a patient.
 */
public class PastDiagnosesAndTreatments {
    private String patientID;
    private String diagnosis;
    private String treatment;

    /**
     * Creates an instance of PastDiagnosesAndTreatments with the provided patient ID, diagnosis, and treatment.
     *
     * @param patientID The ID of the patient.
     * @param diagnosis The diagnosis of the patient.
     * @param treatment The treatment prescribed to the patient.
     */
    public PastDiagnosesAndTreatments(String patientID, String diagnosis, String treatment) {
        this.patientID = patientID;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    /**
     * Gets the ID of the patient.
     *
     * @return The patient ID.
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Sets the ID of the patient.
     *
     * @param patientID The patient ID to set.
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * Gets the diagnosis of the patient.
     *
     * @return The diagnosis of the patient.
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the diagnosis of the patient.
     *
     * @param diagnosis The diagnosis to set.
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Gets the treatment prescribed to the patient.
     *
     * @return The treatment prescribed to the patient.
     */
    public String getTreatment() {
        return treatment;
    }

    /**
     * Sets the treatment prescribed to the patient.
     *
     * @param treatment The treatment to set.
     */
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}

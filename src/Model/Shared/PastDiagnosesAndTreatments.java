
public class PastDiagnosesAndTreatments {
    private String patientID;
    private String diagnosis;
    private String treatment;

    public PastDiagnosesAndTreatments(String patientID, String diagnosis, String treatment) {
        this.patientID = patientID;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
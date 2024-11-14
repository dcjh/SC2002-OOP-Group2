package Model.Shared;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class AppointmentOutcomeRecord {
    private String diagnoses;
    private String treatments;
    private String prescribedMedications;
    private boolean status;
    private String consultationNotes;
    private LocalDateTime updateTimestamps;

    public AppointmentOutcomeRecord(String diagnoses, String treatments, String prescribedMedications, String consultationNotes, LocalDateTime updateTimestamps) {
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
        this.updateTimestamps = updateTimestamps;
        this.status = false; //false = pending
    }
    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public String getPrescribedMedications() {
        return prescribedMedications;
    }

    public void setPrescribedMedications(String prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getUpdateTimestamps() {
        return updateTimestamps;
    }

    public void setUpdateTimestamps(LocalDateTime updateTimestamps) {
        this.updateTimestamps = updateTimestamps;
    }
}

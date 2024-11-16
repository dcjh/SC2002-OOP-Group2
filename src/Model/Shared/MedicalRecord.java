package Model.Shared;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import Model.Shared.PastDiagnosesAndTreatments;
import Model.Shared.AppointmentOutcomeRecord;

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

    public MedicalRecord(String patientID,String name, String dob, String gender, String phoneNumber, String email, String bloodType, String allergies) {
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

    // String fields
    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public List<AppointmentOutcome> getAppointmentOutcome() {
        return appointmentOutcome;
    }

    public void setAppointmentOutcome(List<AppointmentOutcome> appointmentOutcome) {
        this.appointmentOutcome = appointmentOutcome;
    }
    public List<PastDiagnosesAndTreatments> getPastDiagnosesAndTreatments() {
        return pastDiagnosesAndTreatments;
    }

    public void setPastDiagnosesAndTreatments(List<PastDiagnosesAndTreatments> pastDiagnosesAndTreatments) {
        this.pastDiagnosesAndTreatments = pastDiagnosesAndTreatments;
    }
}
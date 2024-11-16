package Model.Roles;

import java.util.ArrayList;
import java.util.List;

import Model.Shared.Appointment;
import Model.Shared.MedicalRecord;
import Model.Shared.User;

public class Patient extends User {
    private MedicalRecord medicalRecord;
    private List<Appointment> appointments;

    public Patient(String hosID, String password, UserType userType, String name, String gender, int age) {
        super(hosID, password, role, name, gender, age);
        this.medicalRecord=null;
        this.appointments=null
    }
    
    public patient(String hosID, String password, String role, String name, String gender, int age,
                   String dob, String gender, String phoneNumber, String email, String bloodType, String allergies) {
        super(hosID, password, role, name, gender, age);
        this.medicalRecord = new MedicalRecord(hosID, dob, gender, phoneNumber, email, bloodType, allergies);
        this.appointments = new ArrayList<>();
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<AppointmentOutcomeRecord> getPastAppointments() {
        return pastAppointments;
    }

    public void setPastAppointments(List<AppointmentOutcomeRecord> pastAppointments) {
        this.pastAppointments = pastAppointments;
    }
}
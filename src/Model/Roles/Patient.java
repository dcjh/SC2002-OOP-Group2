package Model.Roles;

import java.util.ArrayList;
import java.util.List;

import Model.Shared.Appointment;
import Model.Shared.MedicalRecord;
import Model.Shared.User;

public class Patient extends User {
    private MedicalRecord medicalRecord;
    private List<Appointment> appointments;

    public Patient(String hosID, String password,UserType userType, String name, Gender gender, int age) {
        super(hosID, password,  UserType.PATIENT, name, gender, age);
        this.medicalRecord=null;
        this.appointments=null;
    }

    public Patient(String hosID, String password, UserType userType, String name, Gender gender, int age,
                   String dob, String phoneNumber, String email, String bloodType, String allergies) {
        super(hosID, password, UserType.PATIENT, name, gender, age);
        this.medicalRecord = new MedicalRecord(hosID, name, dob, gender.name(), phoneNumber, email, bloodType, allergies);
        this.appointments = new ArrayList<>();
    }

    
    /** 
     * @return MedicalRecord
     */
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    
    /** 
     * @param medicalRecord
     */
    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getPatientId() {
        return hosID;
    }
    public void setPatientId(String hosID) {
        this.hosID = hosID;
    }

    public String getPassword() {
        return password;
        //using auth
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

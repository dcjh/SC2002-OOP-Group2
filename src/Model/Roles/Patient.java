package Model.Roles;

import java.util.ArrayList;
import java.util.List;

import Model.Shared.Appointment;
import Model.Shared.MedicalRecord;
import Model.Shared.User;

/**
 * The Patient class represents a patient in the hospital system.
 * It extends the User class and includes additional information specific to patients,
 * such as medical records and a list of appointments.
 */
public class Patient extends User {

    private MedicalRecord medicalRecord;
    private List<Appointment> appointments;

    /**
     * Constructor to create a new Patient with basic information.
     * 
     * @param hosID     The hospital ID for the patient.
     * @param password  The password for patient login.
     * @param userType  The user type, which is always UserType.PATIENT for this class.
     * @param name      The patient's name.
     * @param gender    The patient's gender.
     * @param age       The patient's age.
     */
    public Patient(String hosID, String password, UserType userType, String name, Gender gender, int age) {
        super(hosID, password, UserType.PATIENT, name, gender, age);
        this.medicalRecord = null;
        this.appointments = null;
    }

    /**
     * Constructor to create a new Patient with full details including medical record.
     * 
     * @param hosID        The hospital ID for the patient.
     * @param password     The password for patient login.
     * @param userType     The user type, which is always UserType.PATIENT for this class.
     * @param name         The patient's name.
     * @param gender       The patient's gender.
     * @param age          The patient's age.
     * @param dob          The patient's date of birth.
     * @param phoneNumber  The patient's phone number.
     * @param email        The patient's email address.
     * @param bloodType    The patient's blood type.
     * @param allergies    Any known allergies of the patient.
     */
    public Patient(String hosID, String password, UserType userType, String name, Gender gender, int age,
                   String dob, String phoneNumber, String email, String bloodType, String allergies) {
        super(hosID, password, UserType.PATIENT, name, gender, age);
        this.medicalRecord = new MedicalRecord(hosID, name, dob, gender.name(), phoneNumber, email, bloodType, allergies);
        this.appointments = new ArrayList<>();
    }

    /**
     * Gets the patient's medical record.
     * 
     * @return The patient's medical record.
     */
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    /**
     * Sets the patient's medical record.
     * 
     * @param medicalRecord The medical record to be set for the patient.
     */
    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    /**
     * Gets the list of appointments for the patient.
     * 
     * @return The list of appointments.
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Sets the list of appointments for the patient.
     * 
     * @param appointments The list of appointments to be set.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Gets the patient's hospital ID.
     * 
     * @return The patient's hospital ID.
     */
    public String getPatientId() {
        return hosID;
    }

    /**
     * Sets the patient's hospital ID.
     * 
     * @param hosID The hospital ID to set for the patient.
     */
    public void setPatientId(String hosID) {
        this.hosID = hosID;
    }

    /**
     * Gets the patient's password.
     * 
     * @return The patient's password.
     */
    public String getPassword() {
        return password;  // Using auth
    }

    /**
     * Sets the patient's password.
     * 
     * @param password The password to set for the patient.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's type.
     * 
     * @return The user type, which is UserType.PATIENT for this class.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Gets the patient's name.
     * 
     * @return The patient's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the patient's name.
     * 
     * @param name The name to set for the patient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the patient's gender.
     * 
     * @return The patient's gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the patient's age.
     * 
     * @return The patient's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the patient's age.
     * 
     * @param age The age to set for the patient.
     */
    public void setAge(int age) {
        this.age = age;
    }
}

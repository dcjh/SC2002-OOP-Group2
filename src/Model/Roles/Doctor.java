package Model.Roles;

import Model.Shared.User;

/**
 * The Doctor class represents a doctor user in the hospital system.
 * It extends the User class and provides doctor-specific fields and methods.
 */
public class Doctor extends User {

    /**
     * Constructs a Doctor object with specified attributes.
     * 
     * @param hosID The hospital ID of the doctor.
     * @param password The password for the doctor.
     * @param userType The type of user (set as DOCTOR by default).
     * @param name The name of the doctor.
     * @param gender The gender of the doctor.
     * @param age The age of the doctor.
     */
    public Doctor(String hosID, String password, UserType userType, String name, Gender gender, int age) {
        super(hosID, password, UserType.DOCTOR, name, gender, age);
    }
    
    /**
     * Gets the doctor ID.
     * 
     * @return The hospital ID of the doctor.
     */
    public String getDoctorId() {
        return hosID;
    }
    
    /**
     * Sets the doctor ID.
     * 
     * @param hosID The hospital ID to set.
     */
    public void setDoctorId(String hosID) {
        this.hosID = hosID;
    }

    /**
     * Gets the password of the doctor.
     * 
     * @return The password of the doctor.
     */
    public String getPassword() {
        return password;
        // using auth
    }

    /**
     * Sets the password of the doctor.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user type of the doctor.
     * 
     * @return The user type (DOCTOR).
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Gets the name of the doctor.
     * 
     * @return The name of the doctor.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the doctor.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gender of the doctor.
     * 
     * @return The gender of the doctor.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the age of the doctor.
     * 
     * @return The age of the doctor.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the doctor.
     * 
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }
}

package Model.Roles;

import Model.Shared.User;

/**
 * The Pharmacist class represents a pharmacist in the hospital system.
 * It extends the User class and includes information specific to pharmacists.
 */
public class Pharmacist extends User {

    /**
     * Constructor to create a new Pharmacist with basic information.
     * 
     * @param hosID     The hospital ID for the pharmacist.
     * @param password  The password for pharmacist login.
     * @param userType  The user type, which is always UserType.PHARMACIST for this class.
     * @param name      The pharmacist's name.
     * @param gender    The pharmacist's gender.
     * @param age       The pharmacist's age.
     */
    public Pharmacist(String hosID, String password, UserType userType, String name, Gender gender, int age) {
        super(hosID, password, UserType.PHARMACIST, name, gender, age);
    }

    /**
     * Gets the pharmacist's hospital ID.
     * 
     * @return The pharmacist's hospital ID.
     */
    public String getPharmacistId() {
        return hosID;
    }

    /**
     * Sets the pharmacist's hospital ID.
     * 
     * @param hosID The hospital ID to set for the pharmacist.
     */
    public void setPharmacistId(String hosID) {
        this.hosID = hosID;
    }

    /**
     * Gets the pharmacist's password.
     * 
     * @return The pharmacist's password.
     */
    public String getPassword() {
        return password;  // Using auth
    }

    /**
     * Sets the pharmacist's password.
     * 
     * @param password The password to set for the pharmacist.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's type.
     * 
     * @return The user type, which is UserType.PHARMACIST for this class.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Gets the pharmacist's name.
     * 
     * @return The pharmacist's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the pharmacist's name.
     * 
     * @param name The name to set for the pharmacist.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the pharmacist's gender.
     * 
     * @return The pharmacist's gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the pharmacist's age.
     * 
     * @return The pharmacist's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the pharmacist's age.
     * 
     * @param age The age to set for the pharmacist.
     */
    public void setAge(int age) {
        this.age = age;
    }
}

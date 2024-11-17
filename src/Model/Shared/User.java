package Model.Shared;

import Model.Roles.UserType;
import Model.Roles.Gender;

/**
 * Represents a generic user of the system.
 * <p>
 * This class serves as a base class for different types of users (e.g., Administrator, Doctor, Patient, Pharmacist).
 */
public class User {
    protected String hosID; // Hospital ID, unique identifier for the user
    protected String password;
    protected UserType userType; // Role of the user (Administrator, Doctor, etc.)
    protected String name;
    protected Gender gender;
    protected int age;
    protected String role; // Legacy field, might be replaced by UserType

    /**
     * Constructs a User with the specified details.
     *
     * @param hosID    Unique hospital ID for the user.
     * @param password User's password.
     * @param userType UserType to specify the role of the user.
     * @param name     Name of the user.
     * @param gender   Gender of the user (MALE, FEMALE, OTHERS).
     * @param age      Age of the user.
     */
    public User(String hosID, String password, UserType userType, String name, Gender gender, int age) {
        this.hosID = hosID;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // Getters and Setters

    /**
     * Gets the user's name.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param newName The new name for the user.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the gender of the user.
     *
     * @return The gender of the user.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param newGender The new gender of the user.
     */
    public void setGender(Gender newGender) {
        this.gender = newGender;
    }

    /**
     * Gets the hospital ID of the user.
     *
     * @return The hospital ID.
     */
    public String getHosID() {
        return hosID;
    }

    /**
     * Sets the hospital ID for the user.
     *
     * @param newHosID The new hospital ID.
     */
    public void setHosID(String newHosID) {
        this.hosID = newHosID;
    }

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the user.
     *
     * @param newAge The new age of the user.
     */
    public void setAge(int newAge) {
        this.age = newAge;
    }

    /**
     * Gets the user type (e.g., ADMINISTRATOR, DOCTOR, PATIENT, PHARMACIST).
     *
     * @return The UserType of the user.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Gets the role of the user.
     *
     * @return The role of the user. This might be redundant if UserType is used consistently.
     */
    public String getRole() {
        return role;
    }

    /**
     * Gets the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param newPassword The new password for the user.
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Performs login by validating the provided password.
     *
     * @param inputPassword The password input by the user.
     * @return True if the inputPassword matches the user's password, false otherwise.
     */
    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}

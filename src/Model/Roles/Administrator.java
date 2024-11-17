package Model.Roles;

import Model.Shared.User;

/**
 * The Administrator class represents a hospital administrator.
 * It extends the User class and provides administrator-specific fields and methods.
 */
public class Administrator extends User {
    
    /**
     * Constructs an Administrator object with specified attributes.
     * 
     * @param hosID The hospital ID of the administrator.
     * @param password The password for the administrator.
     * @param userType The type of user (set as ADMINISTRATOR by default).
     * @param name The name of the administrator.
     * @param gender The gender of the administrator.
     * @param age The age of the administrator.
     */
    public Administrator(String hosID, String password, UserType userType, String name, Gender gender, int age) {
        super(hosID, password, UserType.ADMINISTRATOR, name, gender, age);
    }

    /**
     * Gets the administrator ID.
     * 
     * @return The hospital ID of the administrator.
     */
    public String getAdministratorId() {
        return hosID;
    }
    
    /**
     * Sets the administrator ID.
     * 
     * @param hosID The hospital ID to set.
     */
    public void setAdministratorId(String hosID) {
        this.hosID = hosID;
    }

    /**
     * Gets the password of the administrator.
     * 
     * @return The password of the administrator.
     */
    public String getPassword() {
        return password;
        // using auth
    }

    /**
     * Sets the password of the administrator.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user type of the administrator.
     * 
     * @return The user type (ADMINISTRATOR).
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Gets the name of the administrator.
     * 
     * @return The name of the administrator.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the administrator.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gender of the administrator.
     * 
     * @return The gender of the administrator.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the age of the administrator.
     * 
     * @return The age of the administrator.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the administrator.
     * 
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }
}

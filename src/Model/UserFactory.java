package Model;

import Model.Roles.*;
import Model.Shared.*;

/**
 * Factory class to create users based on their type.
 */
public class UserFactory {

    /**
     * Creates a user instance based on the provided user type and parameters.
     * 
     * @param userType The type of user to create (ADMINISTRATOR, DOCTOR, PATIENT, PHARMACIST).
     * @param hosID The unique hospital ID for the user.
     * @param password The password for the user.
     * @param name The name of the user.
     * @param gender The gender of the user.
     * @param age The age of the user.
     * @return An instance of the appropriate User subclass.
     * @throws IllegalArgumentException if the user type is not recognized.
     */
    public static User createUser(UserType userType, String hosID, String password, String name, Gender gender, int age) {
        if (userType == null) {
            throw new IllegalArgumentException("User type cannot be null.");
        }

        switch (userType) {
            case ADMINISTRATOR:
                return new Administrator(hosID, password, userType, name, gender, age);
            case DOCTOR:
                return new Doctor(hosID, password, userType, name, gender, age);
            case PATIENT:
                return new Patient(hosID, password, userType, name, gender, age);
            case PHARMACIST:
                return new Pharmacist(hosID, password, userType, name, gender, age);
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}

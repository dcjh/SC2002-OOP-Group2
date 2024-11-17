package Model;

import Model.Roles.*;
import Model.Shared.*;

public class UserFactory {
    
    /** 
     * @param userType
     * @param hosID
     * @param password
     * @param name
     * @param gender
     * @param age
     * @return User
     */
    public static User createUser(UserType userType, String hosID, String password, String name, Gender gender, int age) {
        switch (userType) {
            case ADMINISTRATOR:
                return new Administrator(hosID, password, userType, name, gender, age);
            case DOCTOR:
                return new Doctor(hosID, password,userType ,name, gender, age);
            case PATIENT:
                return new Patient(hosID, password, userType,name, gender, age);
            case PHARMACIST:
                return new Pharmacist(hosID, password, userType,name, gender, age);
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}

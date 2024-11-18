package Model;

import Model.Shared.User;
import View.Administrator.AdministratorView;
import View.Doctor.DoctorView;
import View.Patient.PatientView;
import View.Pharmacist.PharmacistView;
import View.UserMainView;

/**
 * Factory class to create the appropriate user view based on user role.
 */
public class MenuFactory {

    /**
     * Returns the main view for a specific user based on their user type.
     * 
     * @param user User object containing user information.
     * @return The appropriate UserMainView for the given user type.
     * @throws IllegalArgumentException if the user type is not recognized.
     */
    public static UserMainView getMainView(User user) {
        switch (user.getUserType()) {
            case ADMINISTRATOR:
                return new AdministratorView(user);
            case DOCTOR:
                return new DoctorView(user);
            case PATIENT:
                return new PatientView(user);
            case PHARMACIST:
                return new PharmacistView(user);
            default:
                throw new IllegalArgumentException("No menu available for this user type: " + user.getUserType());
        }
    }
}

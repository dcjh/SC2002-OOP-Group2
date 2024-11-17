package Model;
import Model.Shared.User;
import View.Administrator.AdministratorView;
import View.Doctor.DoctorView;
import View.Patient.PatientView;
import View.Pharmacist.PharmacistView;
import View.UserMainView;


public class MenuFactory {
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
                throw new IllegalArgumentException("No menu available for this user type: " + user);
        }
    }
}

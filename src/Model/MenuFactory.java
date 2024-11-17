package Model;
import View.Administrator.AdministratorView;
import View.Pharmacist.PharmacistView;
import View.UserMainView;
import View.Doctor.DoctorView;
import View.Patient.PatientView;
import Model.Roles.UserType;
import Model.Shared.User;
import Controller.DoctorController;


public class MenuFactory {
    public static UserMainView getMainView(User user) {
        switch (user.getUserType()) {
            case ADMINISTRATOR:
                return new AdministratorView(user);
            case DOCTOR:
                return (new DoctorController(user)).displayDoctorView();
            case PATIENT:
                return new PatientView(user);
            case PHARMACIST:
                return new PharmacistView(user);    
            
            default:
                throw new IllegalArgumentException("No menu available for this user type: " + user);
        }
    }
}

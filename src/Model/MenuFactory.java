package Model;
import View.AdministratorView;
import View.DoctorView;
import View.PatientView;
import View.PharmacistView;
import View.UserMainView;


public class MenuFactory {
    public static UserMainView getMainView(UserType userType) {
        switch (userType) {
            case ADMINISTRATOR:
                return new administratorView();
            case DOCTOR:
                return new doctorView();
            case PATIENT:
                return new patientView();
            case PHARMACIST:
                return new pharmacistView();    
            
            default:
                throw new IllegalArgumentException("No menu available for this user type: " + userType);
        }
    }
}

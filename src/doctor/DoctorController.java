package Doctor;
import Authentication.Authentication;
import Doctor.*;

public class DoctorController {
    private Doctor doctor;
    private DoctorView view;
    private Authentication auth;

    public DoctorController(Doctor doctor, DoctorView view) {
        this.doctor = doctor;
        this.view = view;
        this.auth = auth;
    }

    public boolean login(String hosID, String password) {
        if (auth.authenticate(hosID, password) != null) {
            view.displayLoginSuccess();
            return true;
        } else {
            view.displayLoginFailure();
            return false;
        }
    }

    public void setAvailability() {

    }

    public void viewUpcomingAppointment() {

    }

    public void viewPatientHistory() {

    }

    public void updatePatientRecord() {
        
    }

}

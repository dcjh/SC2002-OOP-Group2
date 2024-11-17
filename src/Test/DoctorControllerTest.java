package Test;
import Controller.DoctorController;
import Controller.PatientController;
import Model.Roles.Patient;

public class DoctorControllerTest {
    
    private static DoctorController doctorController;
    private static PatientController patientController;
    private static Patient patient;

    public static void main(String args[]) {
        doctorController = new DoctorController();
        Patient p = new Patient("P1001", "patient1001",null,"HI", null, 1);
        // doctorController.displayDoctorView("D0002");
        patientController = new PatientController(p);
        patientController.scheduleAppointment();
        // doctorController.
    }
}

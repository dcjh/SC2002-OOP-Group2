package Test;
import Controller.DoctorController;
import Controller.PatientController;
import Model.Roles.Patient;
import Model.Roles.Doctor;

public class DoctorControllerTest {
    
    private static DoctorController doctorController;
    private static PatientController patientController;
    private static Patient patient;

    
    /** 
     * @param args[]
     */
    public static void main(String args[]) {
        Doctor d = new Doctor("D0002","hehe",null,"Dog",null,1);
        doctorController = new DoctorController(d);
        Patient p = new Patient("P1001", "patient1001",null,"HI", null, 1);
        patientController = new PatientController(p);
        patientController.cancelAppointment();
        patientController.rescheduleAppointment();
        doctorController.viewAppointmentRequests();
    }
}

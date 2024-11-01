package doctor;
import user.User;
// import patient.Patient;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends User{
    
    private List<String> appointments;
    private List<Long> availDate ;
    private List<String> patientView;

    public Doctor(String staffID, String password, String role, String name, String gender, int age) {
        super(staffID, password, role, name, gender, age);
        this.appointments = new ArrayList<>();
        this.availDate = new ArrayList<>();
        this.patientView = new ArrayList<>();
    }

    public void viewMenu() {
        System.out.println();
        System.out.println("Doctor Menu:");
        System.out.println("1. View Schedule");
        System.out.println("2. View Appointment Requests");
        System.out.println("3. Patient Records");
        System.out.println("4. Logout");
    }

    public void getAllAppointment() {
        System.out.println("Appointment List:");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println("-----------------------------");
            System.out.println("Patient"  + (i + 1) + ":"); 
            System.out.println(appointments.get(i));
        }
    }

    public void getAppointment(String patientID) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).patientID == patientID) {
                System.out.println("Appointment:");
                System.out.println("-----------------------------");
                System.out.println(appointments.get(i));
                System.out.println("-----------------------------");
                break;
            }
        }
    }

    public void setAppointment() {
        
    }

    public void getAvailability() {
        System.out.println("Appointment List:");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println("-----------------------------");
            System.out.println("Patient"  + (i + 1) + ":"); 
            System.out.println(appointments.get(i));
        }
    }


}

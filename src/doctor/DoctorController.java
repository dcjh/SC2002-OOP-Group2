package Doctor;

import Repository.DoctorRepository;
import Repository.DataClasses.Appointment;
import Repository.DataClasses.MedicalRecord;
import Repository.DataClasses.Availability;
import java.util.List;

public class DoctorController {
    private Doctor doctor;
    private DoctorView view;
    private DoctorRepository repository;

    public DoctorController(Doctor doctor, DoctorView view) {
        this.doctor = doctor;
        this.view = view;
        this.repository = repository;
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

package Controller;

import Model.Roles.Doctor;
import Model.Shared.Appointment;
import Model.Shared.Availability;
import Model.Shared.MedicalRecord;
import View.DoctorView;

import java.util.List;

import Data.Repository.DoctorRepository;

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

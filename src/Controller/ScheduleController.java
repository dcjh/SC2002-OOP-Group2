package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import DataAccess.ScheduleDAO;
import View.Doctor.DoctorScheduleView;
import View.Patient.PatientScheduleView;
import Model.Shared.Schedule;

public class ScheduleController {
    
    private DoctorScheduleView doctorSView;
    private PatientScheduleView patientSView;
    private Schedule model;
    private ScheduleDAO data;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ScheduleController() {
        this.data = new ScheduleDAO();
    }

    //universal schedule for both views
    public void 

    //manage schedule for doctorView


    public void fetchById(String doctorId) {

    }

    //manage schedule for patientView
    public void fetchAvailableDates(LocalDate date) {

    }


}

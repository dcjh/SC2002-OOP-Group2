package Controller.ScheduleControllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import Controller.DoctorController;
import Controller.PatientController;
import DataAccess.ScheduleDAO;
import View.Doctor.DoctorScheduleView;
import View.Patient.PatientScheduleView;
import Model.Shared.Schedule;


public class ScheduleController{
    private DoctorController doctorController;
    private PatientController patientController;
    private ScheduleDAO data;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ScheduleController() {
        this.data = new ScheduleDAO();
        this.doctorController = new DoctorController();
        this.patientController = new PatientController();
    }

    public void doctorView() {
        doctorController.menu();
    }

    public void patientView() {

    }

    public Schedule getDoctorSchedule(String doctorId) {
        data.find(doctorId);
    }

}

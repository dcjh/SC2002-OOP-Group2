package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import DataAccess.ScheduleDAO;
import View.Doctor.DoctorScheduleView;
import View.Doctor.DoctorScheduleView;
import View.Patient.PatientScheduleView;
import Model.Shared.Appointment;
import Model.Shared.Schedule;
import Test.appointmentOutcomeTest;


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

    public void viewDoctorSchedule(String doctorId, List<Appointment> appointments) {
        doctorScheduleView(doctorId, data.find(doctorId), appointments);
    }

    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable) {
        data.updateIsAvailable(doctorId, date, isAvailable);
    }

    //view navigation
    public void doctorView(String doctorId) {
       doctorController.doctorView(doctorId);
    }
    public void patientView(String patientId) {
        patientController.patientView(patientId);
    }
    public void patientScheduleView(String patientId) {
        PSVController.patientScheduleView(patientId);
    }
    public void doctorScheduleView (String doctorId, Schedule schedule, List<Appointment> appointments) {
        DSVController.doctorScheduleView(doctorId, schedule, appointments);
    }
    public void doctorAvailabilityView (String doctorId) {
        DSVController.doctorAvailabilityView(doctorId);
    }
    //
}

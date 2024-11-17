package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Data.DataAccess.ScheduleDAO;
import View.Doctor.DoctorScheduleView;
import View.Patient.PatientScheduleView;
import View.Patient.PatientBookScheduleView;
import View.Patient.PatientCancelView;
import View.Patient.PatientReScheduleView;
import View.Doctor.DoctorAvailabilityView;
import Model.Shared.Appointment;
import Model.Shared.Schedule;

public class ScheduleController{

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private DoctorController doctorController;
    private PatientController patientController;
    private DoctorAvailabilityView doctorAvailabilityView;
    private PatientScheduleView patientScheduleView;
    private PatientReScheduleView patientReScheduleView;
    private PatientCancelView patientCancelView;
    private DoctorScheduleView doctorScheduleView;
    private ScheduleDAO data;
    private PatientBookScheduleView patientBookScheduleView;

    public ScheduleController(DoctorController doctorController, PatientController patientController) {
        this.data = new ScheduleDAO();
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.doctorScheduleView = new DoctorScheduleView();
        this.doctorAvailabilityView = new DoctorAvailabilityView(this);
        this.patientScheduleView = new PatientScheduleView();
        this.patientBookScheduleView = new PatientBookScheduleView(this);
        this.patientReScheduleView = new PatientReScheduleView(this);
        this.patientCancelView = new PatientCancelView(this);
    }
    
    //navigate to DoctorScheduleView
    public void showDoctorSchedule(String doctorId) {
        Schedule schedule = data.find(doctorId);
        if (schedule == null) {
            System.out.println("----------------------------------------------");
            System.out.println("No schedule found for Doctor ID: " + doctorId);
            System.out.println("Please set your schedule~~~");
            System.out.println("----------------------------------------------");
            return;
        }
        List<Appointment> allAppointments = doctorController.getAppointmentsById(doctorId);
        HashMap<LocalDate , Appointment> Appointments = new HashMap<>();
        for (LocalDate date : schedule.getDateAvailability().keySet()) {
            for (Appointment appointment : allAppointments) {
                if (appointment.getDate().equals(date.format(DATE_FORMAT)) && appointment.getStatus().equals("approved")) {
                    Appointments.put(date, appointment);
                    break;
                }
            }
        }
        // Pass consolidated data to the view
        doctorScheduleView.menu(doctorId, schedule.getDateAvailability(), schedule.getDateTime(), Appointments);
    }

    //navigate to setAvailabilityView
    public void showSetAvailabilityView(String doctorId){
        doctorAvailabilityView.menu(doctorId);
    }

    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable, String time, Boolean doctorUse) {
        if (data.find(doctorId) == null ) {
            Schedule newSchedule = new Schedule(doctorId);
            newSchedule.getDateAvailability().put(date, isAvailable);
            newSchedule.getDateTime().put(date, time);
            data.add(newSchedule);
        } else { 
            Boolean validAppt = false;
            
            for (Appointment a : doctorController.getAppointmentsById(doctorId)) {
                if (a.getDate().equals(date.format(DATE_FORMAT)))  { //if existing appointment
                   validAppt = true;
                }
            }
            
            if(doctorUse == false) validAppt = false;

            if(validAppt) {
                System.out.println("You have an appointment on that date! Unable to set availability");
            } else {
                data.updateIsAvailable(doctorId, date, isAvailable, time); 
            }
        } 
        
    }

    //navigate to PatientScheduleView
    public void patientScheduleView() {
        patientScheduleView.menu(findAllAvailableDoctors());
    }

    public void patientBookScheduleView(String patientId) {
        patientBookScheduleView.menu(findAllAvailableDoctors(), patientId);
    }

    public void patientReScheduleView(String patientId) {
        patientReScheduleView.menu(getAllConfirmedAppointments(), patientId);
    }

    public void patientCancelView(String patientId) {
        patientCancelView.menu(patientController.getConfirmedAppointmentsByPatientId(), patientId);
    }

    //helper logic
    public List<Schedule> findAllAvailableDoctors() {
        List<Schedule> scheduleList = new ArrayList<>();
        for (Schedule s : data.fetch()) {
            if (s.getDateAvailability().containsValue(true)) {
                scheduleList.add(s);
            }
        }
        return scheduleList;
    }

    public void createAppointmentRequest(String doctorId, String date, String time) {
        patientController.createAppointment(doctorId, date, time);
    }

    public void cancelAppointment(String appointmentId, String doctorId, String date, String time){
        patientController.cancelAppointmentHandler(appointmentId);
        updateDoctorSchedule(doctorId, LocalDate.parse(date, DATE_FORMAT), true, time, false);
    }

    public List<Appointment> getAllConfirmedAppointments() {
        return patientController.getConfirmedAppointmentsByPatientId();
    }

    public void rescheduleAppointment(String appointmentId, String doctorId, String oldDate, String newDate, String time) {
        patientController.rescheduleAppointment(appointmentId, newDate, time);
        updateDoctorSchedule(doctorId, LocalDate.parse(oldDate, DATE_FORMAT), true, time, false);
    }

}

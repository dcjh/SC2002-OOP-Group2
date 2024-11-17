package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Data.DataAccess.ScheduleDAO;
import View.Doctor.DoctorScheduleView;
import View.Patient.PatientScheduleView;
import View.Doctor.DoctorAvailabilityView;
import Model.Shared.Appointment;
import Model.Shared.Schedule;

public class ScheduleController{

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private DoctorController doctorController;
    private PatientController patientController;
    private DoctorAvailabilityView doctorAvailabilityView;
    private PatientScheduleView patientScheduleView;
    private DoctorScheduleView doctorScheduleView;
    private ScheduleDAO data;

    public ScheduleController(DoctorController doctorController, PatientController patientController) {
        this.data = new ScheduleDAO();
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.doctorScheduleView = new DoctorScheduleView();
        this.doctorAvailabilityView = new DoctorAvailabilityView(this);
        this.patientScheduleView = new PatientScheduleView(this);
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

    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable, String time) {
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

    public void patientReScheduleView() {
        
    }

    public List<Schedule> findAllAvailableDoctors() {
        List<Schedule> scheduleList = new ArrayList<>();
        for (Schedule s : data.fetch()) {
            if (s.getDateAvailability().containsValue(true)) {
                scheduleList.add(s);
            }
        }
        return scheduleList;
    }

}

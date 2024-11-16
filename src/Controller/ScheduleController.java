package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Data.DataAccess.ScheduleDAO;
import View.Doctor.DoctorScheduleView;
import View.Doctor.DoctorAvailabilityView;
import Model.Shared.Appointment;
import Model.Shared.Schedule;

public class ScheduleController{

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private DoctorController doctorController;
    private DoctorAvailabilityView doctorAvailabilityView;
    private DoctorScheduleView doctorScheduleView;
    private ScheduleDAO data;
    
    public ScheduleController(DoctorController doctorController) {
        this.data = new ScheduleDAO();
        this.doctorController = doctorController;
        this.doctorScheduleView = new DoctorScheduleView();
        this.doctorAvailabilityView = new DoctorAvailabilityView(this);
    }

    public void viewDoctorSchedule(String doctorId) {
        Schedule schedule = data.find(doctorId);
        if (schedule == null) {
            System.out.println("No schedule found for Doctor ID: " + doctorId);
            System.out.println("Please set your schedule~~~");
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
        doctorScheduleView.menu(doctorId, schedule.getDateAvailability(), Appointments);
    }

    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable) {
        if (data.find(doctorId) == null ) {
            Schedule newSchedule = new Schedule(doctorId);
            newSchedule.getDateAvailability().put(date, isAvailable);
            data.add(newSchedule);
        } else { data.updateIsAvailable(doctorId, date, isAvailable); }
    }

    public void showSetAvailabilityView(String doctorId){
        doctorAvailabilityView.menu(doctorId);
    }

    public void returnToDoctorView(String doctorId){
        doctorController.displayDoctorView(doctorId);
    }

}

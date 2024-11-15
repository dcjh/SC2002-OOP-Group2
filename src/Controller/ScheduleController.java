package Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import Data.DataAccess.ScheduleDAO;
import View.Doctor.DoctorScheduleView;
import View.Doctor.DoctorAvailabilityView;
import Model.Shared.Appointment;
import Model.Shared.Schedule;

public class ScheduleController{

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
        List<Appointment> allAppointments = doctorController.getAppointmentsById(doctorId);
        
        // Loop over each date in the schedule and pass filtered appointments to the view
        schedule.getDateAvailability().forEach((date, isAvailable) -> {
            List<Appointment> dailyAppointments = allAppointments.stream()
                .filter(appointment -> appointment.getDate().equals(date))
                .collect(Collectors.toList());

            // Call the view to display schedule for this specific date
            doctorScheduleView.menu(doctorId, date, isAvailable, dailyAppointments);
        });
    }

    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable) {
        data.updateIsAvailable(doctorId, date, isAvailable);
    }

    public void showSetAvailabilityView(String doctorId){
        doctorAvailabilityView.menu(doctorId);
    }

    public void returnToDoctorView(){

    }

}

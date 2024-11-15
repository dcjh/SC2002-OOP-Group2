package Controller.ScheduleControllers;

import java.time.LocalDate;
import View.Doctor.DoctorScheduleView;
import Model.Shared.Schedule;

public class DSVController {
    
    private DoctorScheduleView view;
    private ScheduleController scheduleController;

    //constructors
    public DSVController() { this.view = null; }
    public DSVController(DoctorScheduleView view) { 
        this.view = view; 
        this.scheduleController = new ScheduleController();
    }


    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable) {
        
    }

    public void viewPersonalSchedule(Schedule schedule) {
        view.menu(schedule);
    }

}

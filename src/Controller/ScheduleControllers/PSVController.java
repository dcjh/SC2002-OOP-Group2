package Controller.ScheduleControllers;
package Controller.ScheduleControllers;

import java.time.LocalDate;
import View.Patient.PatientScheduleView;

public class PSVController {
    
    private PatientScheduleView view;
    private 

    //constructors
    public PSVController() { this.view = null; }
    public PSVController(DoctorScheduleView view) { this.view = view; }


    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable) {
        
    }

    public void viewPersonalSchedule(String doctorId) {

    }

}

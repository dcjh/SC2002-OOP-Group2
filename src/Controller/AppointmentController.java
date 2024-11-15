package Controller;
import Model.Shared.Appointment;
import View.AppointmentView;

public class AppointmentController {
    private Appointment model;
    private AppointmentView view;
    
    public AppointmentController(Appointment model){
        this.model = model;
        this.view = new AppointmentView();
    }

    // getter functions

    public String getAppointmentID(){
        return this.model.getAppointmentID();
    }

    public String getPatientID(){
        return this.model.getPatientID();
    }

    public String getDocID(){
        return this.model.getDocID();
    }

    public String getStatus(){
        return this.model.getStatus();
    }

    public String getDate(){
        return this.model.getDate();
    }

    public String getTime(){
        return this.model.getTime();
    }

    // setter functions
    // for status (there are 4 status [pending, cancelled, approved, completed])

    public void setStatus(String newStatus){
        this.model.setStatus(newStatus);
    }

    // for date
    public void setDate(String newDate){
        this.model.setDate(newDate);
    }

    // for time
    public void setTime(String newTime){
        this.model.setTime(newTime);
    }

    // no setter method for patientID and doctorID because no one can not modify the patientID or doctorID

    // view

    public void printAppointment(){
        this.view.printAppointment(getAppointmentID(), getDocID(), getPatientID(), getStatus(), getTime(), getDate());
    }

}

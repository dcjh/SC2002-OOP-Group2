package Controller;
import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import View.AppointmentOutcomeView;
import java.util.ArrayList;

public class AppointmentOutcomeController {
    private AppointmentOutcome model;
    private AppointmentOutcomeView view;

    public AppointmentOutcomeController(AppointmentOutcome model){
        this.model = model;
        this.view = new AppointmentOutcomeView();
    }

    // getter

    public String getAppointmentOutcomeID(){
        return this.model.getAppointmentOutcomeID();
    }

    public String getDate(){
        return this.model.getDate();
    }

    public String getTime(){
        return this.model.getTime();
    }

    public String getTypeOfService(){
        return this.model.getTypeOfService();
    }

    public ArrayList<PrescribedMedication> getPrescribedMedications(){
        return this.model.getPrescribedMedications();
    }

    public String getConsultationNotes(){
        return this.model.getConsultationNotes();
    }

    // setter method to change the status of the medicine

    public void setStatusDispensed(String medicineName){
        this.model.setStatusDispensed(medicineName);
    }
    
    // printing Appointment Outcome record 

    public void printAppointmentOutcome(){
        this.view.printAppointmentOutcome(getAppointmentOutcomeID(), getDate(), getTime(), getTypeOfService(), getPrescribedMedications(), getConsultationNotes());
    }
    
}

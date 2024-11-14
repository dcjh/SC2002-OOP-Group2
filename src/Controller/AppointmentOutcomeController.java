package Controller;
import java.util.ArrayList;

import Model.Shared.Appointment;
import Model.Shared.AppointmentOutcome;
import View.AppointmentOutcomeView;
import View.AppointmentView;

public class AppointmentOutcomeController {
    private AppointmentOutcome model;
    private AppointmentOutcomeView view;

    public AppointmentOutcomeController(AppointmentOutcome model, AppointmentOutcomeView view){
        this.model = model;
        this.view = view;
    }

    // getter

    public String getDate(){
        return this.model.getDate();
    }

    public String getTime(){
        return this.model.getTime();
    }

    public String getTypeOfService(){
        return this.model.getTypeOfService();
    }

    public ArrayList<String> getPrescribedMedications(){
        return this.model.getPrescribedMedications();
    }

    public String getConsultationNotes(){
        return this.model.getConsultationNotes();
    }

    // no setter methods bcs no one can modify the records

    // printing Appointment Outcome record 

    public void printAppointmentOutcome(){
        this.view.printAppointmentOutcome(getDate(), getTime(), getTypeOfService(), getPrescribedMedications(), getConsultationNotes());
    }
    
}

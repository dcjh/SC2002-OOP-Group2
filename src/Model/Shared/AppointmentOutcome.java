package Model.Shared;

import java.util.ArrayList;

// ok by the way, the logic is appointment outcome would only be created after the appointment is completed

public class AppointmentOutcome {
    private String date;
    private String time;
    private String typeOfService;
    private ArrayList<String> prescribedMedications;
    private String consultationNotes;

    public AppointmentOutcome(String date, String time, String typeOfService, ArrayList<String> prescribedMedications, String consultationNotes){
        this.date = date;
        this.time = time;
        this.typeOfService = typeOfService;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
    }

    // getter

    public String getDate(){
        return date;
    }

    public String getTime(){
        return this.time;
    }

    public String getTypeOfService(){
        return this.typeOfService;
    }

    public ArrayList<String> getPrescribedMedications(){
        return this.prescribedMedications;
    }

    public String getConsultationNotes(){
        return this.consultationNotes;
    }

    // no setter methods bcs no one can modify the records

}

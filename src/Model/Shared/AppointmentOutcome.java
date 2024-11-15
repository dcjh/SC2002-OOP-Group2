package Model.Shared;

import java.util.ArrayList;

// ok by the way, the logic is appointment outcome would only be created after the appointment is completed

public class AppointmentOutcome {
    private String date;
    private String time;
    private String typeOfService;
    private ArrayList<PrescribedMedication> prescribedMedications;
    private String consultationNotes;

    // there are 2 possible values for status (status of medicine) ["pending", "dispensed"], if there is an event where the medicine is not in stock yet, status will be pending until stock is replenished and pharmacist is able to dispensed
    // the default value for medicine status is "pending" when Appointment Outcome is first created
    // then it is changed to "dispensed" after the med is given by the pharmacist


    public AppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> prescribedMedications, String consultationNotes){
        this.date = date;
        this.time = time;
        this.typeOfService = typeOfService;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
    }

    // getter

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    public String getTypeOfService(){
        return this.typeOfService;
    }

    public ArrayList<PrescribedMedication> getPrescribedMedications(){
        return this.prescribedMedications;
    }

    public String getConsultationNotes(){
        return this.consultationNotes;
    }

    // setter method to change the status of the medicine

    public void setStatusDispensed(String medicineName){
        for (PrescribedMedication medication : prescribedMedications) {
            if (medication.getMedicineName()==medicineName){
                medication.setStatusDispensed();
                return;
            }
        }
    }

}

package Model.Shared;

import java.util.ArrayList;

// ok by the way, the logic is appointment outcome would only be created after the appointment is completed

public class AppointmentOutcome {
    private static int idCounter = 1; 
    private String appointmentOutcomeID;
    private String doctorID;
    private String patientID;  
    private String appointmentID;  
    private String date;
    private String time;
    private String typeOfService;
    private ArrayList<PrescribedMedication> prescribedMedications;
    private String consultationNotes;

    // there are 2 possible values for status (status of medicine) ["pending", "dispensed"], if there is an event where the medicine is not in stock yet, status will be pending until stock is replenished and pharmacist is able to dispensed
    // the default value for medicine status is "pending" when Appointment Outcome is first created
    // then it is changed to "dispensed" after the med is given by the pharmacist

    public AppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> prescribedMedications, String consultationNotes, String doctorID, String patientID, String appointmentID, String appointmentOutcomeID){
        this.appointmentOutcomeID = appointmentOutcomeID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.appointmentID = appointmentID;
        this.date = date;
        this.time = time;
        this.typeOfService = typeOfService;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
    }

    public AppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> prescribedMedications, String consultationNotes, String doctorID, String patientID, String appointmentID){
        this(date, time, typeOfService, prescribedMedications, consultationNotes, doctorID, patientID, appointmentID, generateFormattedId());
            }
        
            
            /** 
             * @return String
             */
            // Method to generate a formatted ID with prefix and leading zeroes
            private static String generateFormattedId() {
        return String.format("AO%04d", idCounter++); // AO + zero-padded 4-digit ID
    }

    
    /** 
     * @return String
     */
    // getter

    public String getAppointmentOutcomeID(){
        return this.appointmentOutcomeID;
    }

    public String getDoctorID(){
        return this.doctorID;
    }

    public String getPatientID(){
        return this.patientID;
    }

    public String getAppointmentID(){
        return this.appointmentID;
    }

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

    // setter method 

    public void setStatusDispensed(String medicineName){
        for (PrescribedMedication medication : prescribedMedications) {
            if (medication.getMedicineName()==medicineName){
                medication.setStatusDispensed();
                return;
            }
        }
    }

    public void setConsultationNotes(String newConsultationNotes){
        this.consultationNotes = newConsultationNotes;
    }
}

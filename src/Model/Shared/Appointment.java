package Model.Shared;

public class Appointment {
    private static int idCounter = 1; 
    private String appointmentID;  
    private String docID;
    private String patientID;
    private String time;
    private String date;
    private String status;


    // when we initialise appointment, need to input docID, patientID, time, and date as argument
    // when it is initialised, appointment status is initially pending, it will only change to cancelled / approved after it is set by Doctor
    public Appointment(String docID, String patientID, String time, String date, String appointmentID, String status) {
        this.appointmentID = appointmentID;
        this.docID = docID;
        this.patientID = patientID;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Appointment(String docID, String patientID, String time, String date) {
        this(docID, patientID, time, date, generateFormattedId(), "pending");
            }
        
            // Method to generate a formatted ID with prefix and leading zeroes
            private static String generateFormattedId() {
        return String.format("AP%04d", idCounter++); // AP + zero-padded 4-digit ID
    }
    
    // getter functions

    public String getAppointmentID(){
        return this.appointmentID;
    }

    public String getPatientID(){
        return this.patientID;
    }

    public String getDocID(){
        return this.docID;
    }

    public String getStatus(){
        return this.status;
    }

    public Strng getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    // setter functions
    // for status (there are 4 status [pending, cancelled, approved, completed])

    public void setStatus(String newStatus){
        this.status = newStatus;
    }

    // for date
    public void setDate(String newDate){
        this.date = newDate;
    }

    // for time
    public void setTime(String newTime){
        this.time = newTime;
    }

    // no setter method for patientID and doctorID because no one can not modify the patientID or doctorID

}

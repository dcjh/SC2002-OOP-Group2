package Model.Shared;

public class Appointment {
    private String docID;
    private String patientID;
    private String time;
    private String date;
    private String status;

    // when we initialise appointment, need to input docID, patientID, time, and date as argument
    // when it is initialised, appointment status is initially pending, it will only change to cancelled / approved after it is set by Doctor
    public Appointment(String docID, String patientID, String time, String date) {
        this.docID = docID;
        this.patientID = patientID;
        this.status = "pending";
        this.date = date;
        this.time = time;
    }

    // getter functions

    public String getPatientID(){
        return this.patientID;
    }

    public String getDocID(){
        return this.docID;
    }

    public String getStatus(){
        return this.status;
    }

    public String getDate(){
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

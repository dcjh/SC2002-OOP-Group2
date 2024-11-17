package Model.Shared;

import java.time.LocalDate;
import java.util.HashMap;

public class Schedule {
    private String doctorId;
    private HashMap<LocalDate, Boolean> dateAvailability;
    private HashMap<LocalDate, String> dateTime;

    public Schedule(String doctorId) {
        this.doctorId = doctorId;
        this.dateAvailability = new HashMap<>();
        this.dateTime = new HashMap<>();
    }

    public Schedule(String doctorId, HashMap<LocalDate, Boolean> dateAvailability, HashMap<LocalDate, String> dateTime) {
        this.doctorId = doctorId;
        this.dateAvailability = dateAvailability;
        this.dateTime = dateTime;
    }

    //getters
    public String getDoctorId() {
        return doctorId;
    }
    public HashMap<LocalDate, Boolean> getDateAvailability() {
        return dateAvailability;
    }
    public HashMap<LocalDate, String> getDateTime() {
        return dateTime;
    }

    //setters
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    public void setDateAvailability(HashMap<LocalDate, Boolean> dateAvailability) {
        this.dateAvailability = dateAvailability;
    }
    public void setDateTime(HashMap<LocalDate, String> dateTime) {
        this.dateTime = dateTime;
    }

}

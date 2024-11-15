package Model.Shared;

import java.time.LocalDate;
import java.util.HashMap;

public class Schedule {
    private String doctorId;
    private HashMap<LocalDate, Boolean> dateAvailability;

    public Schedule(String doctorId) {
        this.doctorId = doctorId;
        this.dateAvailability = new HashMap<>();
    }

    public Schedule(String doctorId, HashMap<LocalDate, Boolean> dateAvailability) {
        this.doctorId = doctorId;
        this.dateAvailability = dateAvailability;
    }

    //getters
    public String getDoctorId() {
        return doctorId;
    }
    public HashMap<LocalDate, Boolean> getDateAvailability() {
        return dateAvailability;
    }

    //setters
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    public void setDateAvailability(HashMap<LocalDate, Boolean> dateAvailability) {
        this.dateAvailability = dateAvailability;
    }

}

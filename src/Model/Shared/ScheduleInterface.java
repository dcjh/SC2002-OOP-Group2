package Model.Shared;

import java.time.LocalDate;
import java.util.HashMap;

public interface ScheduleInterface {
    //getters
    public String getDoctorId();
    public HashMap<LocalDate, Boolean> getDateAvailability();

    //setters
    public void setDoctorId(String doctorId);
    public void setDateAvailability(HashMap<LocalDate, Boolean> dateAvailability);

}

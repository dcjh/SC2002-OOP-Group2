package View.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import Model.Shared.Schedule;

public class PatientScheduleView {
    
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public PatientScheduleView() {}

    public void menu(List<Schedule> scheduleList) {
        String br = "----------------------------------------------";
        if (scheduleList.isEmpty()) {
            System.out.println(br);
            System.out.println("No Doctors are currently available!");
            System.out.println(br);
        } else {
            System.out.println(br);
            System.out.println("All Available Doctors");
            System.out.println(br);

            for (Schedule s : scheduleList) {
                System.out.println("Doctor ID: " + s.getDoctorId());
                System.out.println(br);
                for (LocalDate date : s.getDateAvailability().keySet()) {
                    if(s.getDateAvailability().get(date)){
                        System.out.println("Date: " + date.format(DATE_FORMAT));
                        System.out.println("Time: " + s.getDateTime().get(date));
                        System.out.println(br);
                    }
                }
            }
        }
    }

}
package View.Doctor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Shared.Appointment;

public class DoctorScheduleView {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public DoctorScheduleView() {}

    
    /** 
     * @param doctorId
     * @param dateAvailability
     * @param dateTime
     * @param appointments
     */
    public void menu(String doctorId, Map<LocalDate, Boolean> dateAvailability, Map<LocalDate, String> dateTime,Map<LocalDate, Appointment> appointments) {
        System.out.println("----------------------------------------------");
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Schedule:");
        System.out.println("----------------------------------------------");
    
        List<LocalDate> sortedDates = new ArrayList<>(dateAvailability.keySet());
        sortedDates.sort(Comparator.naturalOrder());

        for(LocalDate date : sortedDates){
            System.out.println("Date: " + date.format(DATE_FORMAT) + " | Time: " + dateTime.get(date) +
                               " | Availability: " + (dateAvailability.get(date) ? "Available" : "Not Available"));

            if (!appointments.containsKey(date)) {
                System.out.println("  No appointments scheduled for this date.");
            } else {
                System.out.println("  | Patient ID: " + appointments.get(date).getPatientID() +
                                   " | Time: " + appointments.get(date).getTime() + " |");
            }
            System.out.println("----------------------------------------------");
        }
    }
}


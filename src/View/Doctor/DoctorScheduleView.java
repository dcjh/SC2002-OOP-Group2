package View.Doctor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Controller.ScheduleControllers.DSVController;
import Model.Shared.Schedule;
import Model.Shared.Appointment;

public class DoctorScheduleView {
    public DoctorScheduleView() {}

    public void menu(Schedule schedule, Appointment appointment) {
        System.out.println("This is your schedule doctor " + schedule.getDoctorId());
        System.out.println("-----------------------------------------");
        for (HashMap.Entry<LocalDate, Boolean> entry : schedule.getDateAvailability().entrySet()) {
            System.out.println("| Date: " + entry.getKey() + " | Availability: " + entry.getValue() + " |" );
            System.out.println("-----------------------------------------");
        }
    }
}

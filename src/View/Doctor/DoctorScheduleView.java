package View.Doctor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import Model.Shared.Schedule;
import Model.Shared.Appointment;

public class DoctorScheduleView {
    public DoctorScheduleView() {}

    public void menu(String doctorId, LocalDate date, Boolean isAvailable, List<Appointment> appointments) {
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Date: " + date + " | Availability: " + (isAvailable ? "Available" : "Not Available"));
        System.out.println("-----------------------------------------");

        if (appointments.isEmpty()) {
            System.out.println("  No appointments scheduled for this date.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println("  | Patient ID: " + appointment.getPatientID() +
                                   " | Time: " + appointment.getTime() + " |");
            }
        }
        System.out.println("-----------------------------------------");
    }
}

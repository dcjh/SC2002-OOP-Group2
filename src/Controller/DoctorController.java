package Controller;

import View.Doctor.DoctorView;
import Model.Roles.Doctor;
import java.time.LocalDate;
import java.util.List;

import Controller.ScheduleControllers.ScheduleController;

public class DoctorController {

    private DoctorView view;
    private Doctor model;
    private ScheduleController scheduleController;

    public DoctorController() {
        this.scheduleController =  new scheduleController();
    }

    public void viewPersonalSchedule(String doctorId) {
        doctorService.viewPersonalSchedule(doctorId);
    }

    public void setAvailability(String doctorId, LocalDate date, int slotIndex, boolean isAvailable) {
        boolean success = doctorService.setAvailability(doctorId, date, slotIndex, isAvailable);
        if (success) {
            System.out.println("Availability updated successfully.");
        } else {
            System.out.println("Failed to update availability.");
        }
    }

    public void viewAvailableSlots(String doctorId, LocalDate date) {
        List<String> availableSlots = doctorService.getAvailableSlots(doctorId, date);
        if (availableSlots.isEmpty()) {
            System.out.println("No slots available on " + date);
        } else {
            System.out.println("Available slots on " + date + ": " + availableSlots);
        }
    }

}

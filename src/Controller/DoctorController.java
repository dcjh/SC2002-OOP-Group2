package Controller;

import java.time.LocalDate;
import java.util.List;

import Model.Shared.Appointment;
import View.Doctor.DoctorView;

public class DoctorController {

    private DoctorView doctorView;
    private ScheduleController scheduleController;
    private AppointmentController appointmentController;

    public DoctorController() {
        this.scheduleController =  new ScheduleController(this);
        this.appointmentController = new AppointmentController(this);
        this.doctorView = new DoctorView(this);
    }

    //methods to relay data to DoctorView
    public void displayPatientMR() {

    }

    //methods to trigger actions
    public void viewPatientMR() {

    }

    public void updatePatientMR() {

    }
    public void viewDoctorSchedule(String doctorId) {
        scheduleController.viewDoctorSchedule(doctorId);
    }

    public void setAvailability(String doctorId) {
        scheduleController.showSetAvailabilityView(doctorId);
    }
    public void viewAppointmentRequests(String doctorId) {

    }

    public void viewUpcomingAppointments() {

    }

    public void recordAppointmentOutcome() {

    }

    public void logout() {

    }




    public List<Appointment> getAppointmentsById(String doctorId) {
        return appointmentController.getAllAppointment();
    }

}

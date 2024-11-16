package Controller;

import Model.Shared.Appointment;
import View.Doctor.DoctorView;
import java.util.List;

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
    public void displayDoctorView(String doctorId) {
        doctorView.menu(doctorId);
    }

    //methods to trigger actions
    public void viewPatientMR() {

    }

    public void updatePatientMR() {

    }
    public void viewDoctorSchedule(String doctorId) {
        scheduleController.viewDoctorSchedule(doctorId); //delegate to schedule view
    }

    public void setAvailability(String doctorId) {
        scheduleController.showSetAvailabilityView(doctorId); //delegate to ability view
    }
    public void viewAppointmentRequests(String doctorId) {

    }

    public void viewUpcomingAppointments(String doctorId) {
        appointmentController.viewApprovedAppointmentsByDoctorID(doctorId);
    }

    public void recordAppointmentOutcome() {
    }

    public void logout() {

    }




    public List<Appointment> getAppointmentsById(String doctorId) {
        return appointmentController.getAppointmentsByDoctorID(doctorId);
    }

}

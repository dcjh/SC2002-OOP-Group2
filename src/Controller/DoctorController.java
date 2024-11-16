package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
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

    //
    public void displayDoctorView(String doctorId) {
        doctorView.menu(doctorId);
    }

    //methods to trigger actions
    public void viewPatientMR() {

    }

    public void updatePatientMR() {

    }
    public void viewDoctorSchedule(String doctorId) {
        scheduleController.showDoctorSchedule(doctorId); //delegate to schedule view
    }

    public void setAvailability(String doctorId) {
        scheduleController.showSetAvailabilityView(doctorId); //delegate to ability view
    }
    public void viewAppointmentRequests(String doctorId) {
        appointmentController.appointmentRequestsView(doctorId); //delegate to appointment request view
    }

    public void viewUpcomingAppointments(String doctorId) {
        appointmentController.viewApprovedAppointmentsByDoctorID(doctorId);
    }

    public void recordAppointmentOutcome(String doctorId) {

    }

    public void logout() {

    }



    //logic
    public List<Appointment> getAppointmentsById(String doctorId) {
        List<Appointment> allAppointments = appointmentController.getAllAppointment();
        List<Appointment> appointmentsById = new ArrayList<>();
        for (Appointment a : allAppointments) {
            if (a.getDocID().equals(doctorId)) {
                appointmentsById.add(a);
            }
        }
        return appointmentsById;
    }

    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable) {
        scheduleController.updateDoctorSchedule(doctorId, date, isAvailable);
    }

}

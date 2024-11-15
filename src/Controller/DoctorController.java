package Controller;

import View.Doctor.DoctorView;
import Model.Roles.Doctor;
import java.time.LocalDate;
import java.util.List;

import Model.Shared.Schedule;
import Test.appointmentTest;
import Model.Shared.Appointment;
import Model.Roles.Doctor;
import DataAccess.StaffDAO;

public class DoctorController {

    private DoctorView doctorView;
    private ScheduleController scheduleController;
    private AppointmentController appointmentController;
    private StaffDAO staffData; 

    public DoctorController() {
        this.scheduleController =  new ScheduleController();
        this.appointmentController = new AppointmentController();
        this.doctorView = new DoctorView();
        this.staffData = new StaffDAO();
    }

    //menu functions
    public void viewPatientMR() {

    }

    public void updatePatientMR() {

    }

    public void viewDoctorSchedule(String doctorId) {
        scheduleController.viewDoctorSchedule(doctorId, appointment);
    }

    public void setAvailability(String doctorId, LocalDate date, Boolean isAvailable) {
        scheduleController.updateDoctorSchedule(doctorId, date, isAvailable);
    }

    public void viewAppointmentRequests() {

    }

    public void viewUpcomingAppointments() {

    }

    public void recordAppointmentOutcome() {

    }

    public void logout() {

    }

    //view navigation
    public void doctorView(String doctorId) {
        doctorView.menu(doctorId);
    }
    public void doctorScheduleView(String doctorId, Schedule schedule) {
        scheduleController.doctorScheduleView(doctorId, schedule, appointmentController.getAllAppointment());
    }
    public void setAvailabilityView(String doctorId) {
        scheduleController.doctorAvailabilityView(doctorId);
    }
    public void appointmentOutcomeView() {

    }
    public void appointmentView() {
        
    }
    public void appointmentRequestsView() {
        
    }
    public void medicalRecordsView() {
        
    }
    public void setMedicalRecordsView() {
        
    }
    //
}

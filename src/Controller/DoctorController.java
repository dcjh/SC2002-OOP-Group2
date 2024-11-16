package Controller;

import java.time.LocalDate;
import Model.Shared.Appointment;
import View.Doctor.DoctorView;
import java.util.List;

public class DoctorController {

    private DoctorView doctorView;
    private ScheduleController scheduleController;
    private AppointmentController appointmentController;
    private MedicalRecordController medicalRecordController;
    private AppointmentOutcomeController appointmentOutcomeController;

    public DoctorController() {
        this.scheduleController =  new ScheduleController(this);
        this.appointmentController = new AppointmentController(this);
        this.medicalRecordController = new MedicalRecordController(this);
        this.appointmentOutcomeController = new AppointmentOutcomeController(this);
        this.doctorView = new DoctorView(this);
        this.appointmentOutcomeController = new AppointmentOutcomeController(this,appointmentController);
    }

    //navigate to DoctorView
    public void displayDoctorView(String doctorId) {
        doctorView.menu(doctorId);
    }

    //methods to trigger actions
    public void viewPatientMR(String doctorId) {
        medicalRecordController.doctorMedicalRecordView(doctorId);
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

    public void viewRecordAppointmentOutcome(String doctorId) {
        appointmentOutcomeController.viewMenuDoctor(doctorId);
    }

    public void logout() {

    }



    //logic
    public List<Appointment> getAppointmentsById(String doctorId) {
        return appointmentController.getAppointmentsByDoctorID(doctorId);
    }

    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable) {
        scheduleController.updateDoctorSchedule(doctorId, date, isAvailable);
    }
    public List<AppointmentOutcomeRecord> getAppointmentOutcomeById (String doctorId) {
        return appointmentOutcomeController.getAppointmentOutcomeByDoctorID(doctorId);
    }

}

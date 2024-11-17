package Controller;

import java.time.LocalDate;
import Model.Shared.Appointment;
import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import View.Doctor.DoctorView;
import View.UserMainView;
import Model.Shared.User;
import Model.Roles.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorController {

    private Doctor doctor;
    private DoctorView doctorView;
    private ScheduleController scheduleController;
    private AppointmentController appointmentController;
    private MedicalRecordController medicalRecordController;
    private AppointmentOutcomeController appointmentOutcomeController;


    public DoctorController(User user) {
        this.doctor = (Doctor) user;
        this.scheduleController =  new ScheduleController(this, null);
        this.appointmentController = new AppointmentController(this);
        this.medicalRecordController = new MedicalRecordController(this);
        this.appointmentOutcomeController = new AppointmentOutcomeController(appointmentController);
        this.doctorView = new DoctorView(this, doctor);
    }

    //navigate to DoctorView
    public UserMainView displayDoctorView() {
        doctorView.menu(doctor.getDoctorId());
        return doctorView;
    }

    //methods to trigger actions
    public void viewPatientMR(String doctorId) {
        medicalRecordController.doctorMedicalRecordView(doctorId);
    }

    public void updatePatientMR(String doctorId) {
        medicalRecordController.updateMedicalRecordView(doctorId);
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

    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable, String time) {
        scheduleController.updateDoctorSchedule(doctorId, date, isAvailable, time);
    }
    public List<AppointmentOutcome> getAppointmentOutcomeByDoctorId (String doctorId) {
        return appointmentOutcomeController.getAppointmentOutcomeByDoctorID(doctorId);
    }

    public List<AppointmentOutcome> getAppointmentOutcomeByPatientId (String patientId) {
        return appointmentOutcomeController.getAppointmentOutcomeByPatientID(patientId);
    }

    public void createAppointmentOutcome(String date ,String time,String typeOfService,ArrayList<PrescribedMedication> medications, String notes, String doctorId, String patientId, String appointmentId) {
        appointmentOutcomeController.createAppointmentOutcome(date, time, typeOfService, medications, notes, doctorId, patientId, appointmentId);
    }
    

}

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
    public void viewPatientMR() {
        medicalRecordController.doctorMedicalRecordView(doctor.getDoctorId());
    }

    public void updatePatientMR() {
        medicalRecordController.updateMedicalRecordView(doctor.getDoctorId());
    }
    
    public void viewDoctorSchedule() {
        scheduleController.showDoctorSchedule(doctor.getDoctorId()); //delegate to schedule view
    }

    public void setAvailability() {
        scheduleController.showSetAvailabilityView(doctor.getDoctorId()); //delegate to ability view
    }
    public void viewAppointmentRequests() {
        appointmentController.appointmentRequestsView(doctor.getDoctorId()); //delegate to appointment request view
    }

    public void viewUpcomingAppointments() {
        appointmentController.viewApprovedAppointmentsByDoctorID(doctor.getDoctorId());
    }

    public void viewRecordAppointmentOutcome() {
        appointmentOutcomeController.viewMenuDoctor(doctor.getDoctorId());
    }

    public void logout() {

    }

    //logic
    public List<Appointment> getAppointmentsById(String doctorId) {
        return appointmentController.getAppointmentsByDoctorID(doctorId);
    }

    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable, String time) {
        scheduleController.updateDoctorSchedule(doctorId, date, isAvailable, time, true);
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

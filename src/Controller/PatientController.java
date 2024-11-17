package Controller;


import java.time.LocalDate;
import java.util.List;
import Controller.AppointmentController;
import Controller.MedicalRecordController;
import Controller.ScheduleController;
import Model.Roles.Patient;
import Model.Shared.Appointment;


public class PatientController {
    private Patient patient;
    private MedicalRecordController medicalRecordController;
    private AppointmentController appointmentController;
    private ScheduleController scheduleController;
    private DoctorController doctorController;

    public PatientController(Patient patient) {
        this.patient = patient;
        this.medicalRecordController = new MedicalRecordController();
        this.appointmentController = new AppointmentController();
        this.doctorController = new DoctorController(null);
        this.scheduleController = new ScheduleController(doctorController, this);

    }

    public void updateContactInformation(String newPhoneNumber, String newEmail) {
        medicalRecordController.updateContactInformation(patient.getPatientId(), newPhoneNumber, newEmail);
    }
    public void seeSchedule() {
        scheduleController.patientScheduleView();
    }
    public void scheduleAppointment() {
        scheduleController.patientBookScheduleView(patient.getPatientId());
    }
    public void rescheduleAppointment() {
        scheduleController.patientReScheduleView(patient.getPatientId());
    }
    public void cancelAppointment() {
        scheduleController.patientCancelView(patient.getPatientId());
    }
    public void viewScheduledAppointments() {
        appointmentController.viewAppointmentsByPatientID(patient.getPatientId());
    }
    public void viewMedicalRecord() {
        medicalRecordController.viewMedicalRecord(patient.getPatientId());
    }
    public void viewPastAppointments() {
        medicalRecordController.viewPastAppointments(patient.getPatientId());//need to chnage once the dao have been added for apptoutcomerecord
    }

    //navigate to viewing all doctor schedules
    public void createAppointment(String docID, String date, String time) {
        appointmentController.createAppointment(docID, patient.getPatientId(), time, date);
    }

    public void updateRescheduleAppointment(String appointmentID, String newDate, String newTime) {
        appointmentController.updateAppointmentReschedule(appointmentID, newDate, newTime);
    }

    public void updateCancelledAppointment(String appointmentID) {
        appointmentController.updateAppointmentStatus(appointmentID, "cancelled");
    }

    //logic
    public List<Appointment> getConfirmedAppointmentsByPatientId() {
        return appointmentController.getApprovedAppointmentsByPatientID(patient.getPatientId());
    }

    public void cancelAppointmentHandler(String appointmentId) {
        appointmentController.updateAppointmentStatus(appointmentId, "cancelled");
    }

}

package Controller;

import java.time.LocalDate;
import java.util.List;
import Controller.AppointmentController;
import Controller.MedicalRecordController;
import Controller.ScheduleController;
import Model.Roles.Patient;
import Model.Shared.Appointment;

/**
 * The PatientController class manages all interactions and functionalities available for a patient.
 * This includes scheduling, rescheduling, and canceling appointments, viewing medical records,
 * and updating contact information.
 */
public class PatientController {
    private Patient patient;
    private MedicalRecordController medicalRecordController;
    private AppointmentController appointmentController;
    private ScheduleController scheduleController;
    private DoctorController doctorController;

    /**
     * Constructs a PatientController instance for the specified patient.
     *
     * @param patient the Patient object representing the current logged-in patient
     */
    public PatientController(Patient patient) {
        this.patient = patient;
        this.medicalRecordController = new MedicalRecordController();
        this.appointmentController = new AppointmentController();
        this.doctorController = new DoctorController(null);
        this.scheduleController = new ScheduleController(doctorController, this);
    }

    /**
     * Updates the contact information (phone number and email) for the patient.
     *
     * @param newPhoneNumber the new phone number to be updated
     * @param newEmail       the new email to be updated
     */
    public void updateContactInformation(String newPhoneNumber, String newEmail) {
        medicalRecordController.updateContactInformation(patient.getPatientId(), newPhoneNumber, newEmail);
    }

    /**
     * Views the doctor's schedule.
     */
    public void seeSchedule() {
        scheduleController.patientScheduleView();
    }

    /**
     * Schedules a new appointment for the patient.
     */
    public void scheduleAppointment() {
        scheduleController.patientBookScheduleView(patient.getPatientId());
    }

    /**
     * Reschedules an existing appointment for the patient.
     */
    public void rescheduleAppointment() {
        scheduleController.patientReScheduleView(patient.getPatientId());
    }

    /**
     * Cancels an existing appointment for the patient.
     */
    public void cancelAppointment() {
        scheduleController.patientCancelView(patient.getPatientId());
    }

    /**
     * Views all scheduled appointments for the patient.
     */
    public void viewScheduledAppointments() {
        appointmentController.viewAppointmentsByPatientID(patient.getPatientId());
    }

    /**
     * Views the medical record for the patient.
     */
    public void viewMedicalRecord() {
        medicalRecordController.viewMedicalRecord(patient.getPatientId());
    }

    /**
     * Views past appointments for the patient.
     */
    public void viewPastAppointments() {
        medicalRecordController.viewPastAppointments(patient.getPatientId()); // To be changed once DAO for AppointmentOutcomeRecord is added
    }

    /**
     * Creates a new appointment with a specified doctor, date, and time.
     *
     * @param docID the ID of the doctor
     * @param date  the date of the appointment
     * @param time  the time of the appointment
     */
    public void createAppointment(String docID, String date, String time) {
        appointmentController.createAppointment(docID, patient.getPatientId(), time, date);
    }

    /**
     * Reschedules an existing appointment for the patient.
     *
     * @param appointmentID the ID of the appointment to be rescheduled
     * @param newDate       the new date for the appointment
     * @param newTime       the new time for the appointment
     */
    public void updateRescheduleAppointment(String appointmentID, String newDate, String newTime) {
        appointmentController.updateAppointmentReschedule(appointmentID, newDate, newTime);
    }

    /**
     * Cancels an existing appointment for the patient.
     *
     * @param appointmentID the ID of the appointment to be canceled
     */
    public void updateCancelledAppointment(String appointmentID) {
        appointmentController.updateAppointmentStatus(appointmentID, "cancelled");
    }

    /**
     * Retrieves a list of confirmed (approved) appointments for the patient.
     *
     * @return a list of confirmed appointments for the patient
     */
    public List<Appointment> getConfirmedAppointmentsByPatientId() {
        return appointmentController.getApprovedAppointmentsByPatientID(patient.getPatientId());
    }

    /**
     * Handles the cancellation of an appointment for the patient.
     *
     * @param appointmentId the ID of the appointment to be canceled
     */
    public void cancelAppointmentHandler(String appointmentId) {
        appointmentController.updateAppointmentStatus(appointmentId, "cancelled");
    }

    /**
     * Handles the rescheduling of an appointment for the patient.
     *
     * @param appointmentId the ID of the appointment to be rescheduled
     * @param date          the new date for the appointment
     * @param time          the new time for the appointment
     */
    public void rescheduleAppointment(String appointmentId, String date, String time) {
        appointmentController.updateAppointmentReschedule(appointmentId, date, time);
    }
}

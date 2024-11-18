package Controller;

import Model.Roles.Doctor;
import Model.Shared.Appointment;
import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import View.Doctor.DoctorView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The DoctorController class handles all actions that a doctor can perform within the system.
 * It includes viewing and updating schedules, managing appointments, and interacting with patient medical records.
 */
public class DoctorController {

    private Doctor doctor;
    private DoctorView doctorView;
    private ScheduleController scheduleController;
    private AppointmentController appointmentController;
    private MedicalRecordController medicalRecordController;
    private AppointmentOutcomeController appointmentOutcomeController;

    /**
     * Constructs a DoctorController instance.
     *
     * @param doctor the doctor model representing the current logged-in doctor
     */
    public DoctorController(Doctor doctor) {
        this.doctor = doctor;
        this.scheduleController = new ScheduleController(this, null);
        this.appointmentController = new AppointmentController(this);
        this.medicalRecordController = new MedicalRecordController(this);
        this.appointmentOutcomeController = new AppointmentOutcomeController(appointmentController);
    }


    /**
     * Navigates to the view for viewing patient medical records.
     */
    public void viewPatientMR() {
        medicalRecordController.doctorMedicalRecordView(doctor.getDoctorId());
    }

    /**
     * Navigates to the view for updating patient medical records.
     */
    public void updatePatientMR() {
        medicalRecordController.updateMedicalRecordView(doctor.getDoctorId());
    }

    /**
     * Views the doctor's current schedule.
     */
    public void viewDoctorSchedule() {
        scheduleController.showDoctorSchedule(doctor.getDoctorId());
    }

    /**
     * Sets the availability of the doctor for appointments.
     */
    public void setAvailability() {
        scheduleController.showSetAvailabilityView(doctor.getDoctorId());
    }

    /**
     * Views appointment requests made to the doctor.
     */
    public void viewAppointmentRequests() {
        appointmentController.appointmentRequestsView(doctor.getDoctorId());
    }

    /**
     * Views upcoming appointments for the doctor.
     */
    public void viewUpcomingAppointments() {
        appointmentController.viewApprovedAppointmentsByDoctorID(doctor.getDoctorId());
    }

    /**
     * Navigates to the view for recording the outcome of appointments.
     */
    public void viewRecordAppointmentOutcome() {
        appointmentOutcomeController.viewMenuDoctor(doctor.getDoctorId());
    }

    /**
     * Logs out the doctor from the system. (Currently unimplemented)
     */
    public void logout() {
        // Logout logic goes here
    }

    /**
     * Retrieves a list of appointments for the specified doctor.
     *
     * @param doctorId the doctor's ID
     * @return a list of appointments for the specified doctor
     */
    public List<Appointment> getAppointmentsById(String doctorId) {
        return appointmentController.getAppointmentsByDoctorID(doctorId);
    }

    /**
     * Retrieves a list of confirmed (approved) appointments for the specified doctor.
     *
     * @param doctorId the doctor's ID
     * @return a list of confirmed appointments for the specified doctor
     */
    public List<Appointment> getConfirmedAppointmentsById(String doctorId) {
        return appointmentController.getApprovedAppointmentsByDoctorID(doctorId);
    }

    /**
     * Updates the doctor's schedule by setting availability for a specific date and time.
     *
     * @param doctorId    the doctor's ID
     * @param date        the date to update the schedule for
     * @param isAvailable whether the doctor is available at the given time
     * @param time        the time to set availability for
     */
    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable, String time) {
        scheduleController.updateDoctorSchedule(doctorId, date, isAvailable, time, true);
    }

    /**
     * Retrieves appointment outcomes for a specific doctor.
     *
     * @param doctorId the doctor's ID
     * @return a list of appointment outcomes for the specified doctor
     */
    public List<AppointmentOutcome> getAppointmentOutcomeByDoctorId(String doctorId) {
        return appointmentOutcomeController.getAppointmentOutcomeByDoctorID(doctorId);
    }

    /**
     * Retrieves appointment outcomes for a specific patient.
     *
     * @param patientId the patient's ID
     * @return a list of appointment outcomes for the specified patient
     */
    public List<AppointmentOutcome> getAppointmentOutcomeByPatientId(String patientId) {
        return appointmentOutcomeController.getAppointmentOutcomeByPatientID(patientId);
    }

    /**
     * Creates an appointment outcome and saves it in the system.
     *
     * @param date         the date of the appointment outcome
     * @param time         the time of the appointment outcome
     * @param typeOfService the type of service provided during the appointment
     * @param medications  the list of prescribed medications
     * @param notes        consultation notes for the appointment outcome
     * @param doctorId     the doctor's ID
     * @param patientId    the patient's ID
     * @param appointmentId the appointment's ID
     */
    public void createAppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> medications, String notes, String doctorId, String patientId, String appointmentId) {
        appointmentOutcomeController.createAppointmentOutcome(date, time, typeOfService, medications, notes, doctorId, patientId, appointmentId);
    }
}

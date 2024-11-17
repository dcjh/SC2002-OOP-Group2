package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Data.DataAccess.ScheduleDAO;
import View.Doctor.DoctorScheduleView;
import View.Patient.PatientScheduleView;
import View.Patient.PatientBookScheduleView;
import View.Patient.PatientCancelView;
import View.Patient.PatientReScheduleView;
import View.Doctor.DoctorAvailabilityView;
import Model.Shared.Appointment;
import Model.Shared.Schedule;

/**
 * The ScheduleController class is responsible for managing schedules for both doctors and patients.
 * This includes viewing doctor schedules, setting availability, scheduling appointments, and updating appointments.
 */
public class ScheduleController {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private DoctorController doctorController;
    private PatientController patientController;
    private DoctorAvailabilityView doctorAvailabilityView;
    private PatientScheduleView patientScheduleView;
    private PatientReScheduleView patientReScheduleView;
    private PatientCancelView patientCancelView;
    private DoctorScheduleView doctorScheduleView;
    private ScheduleDAO data;
    private PatientBookScheduleView patientBookScheduleView;

    /**
     * Constructs a ScheduleController instance for managing doctor and patient schedules.
     *
     * @param doctorController  the DoctorController instance for doctor-related operations
     * @param patientController the PatientController instance for patient-related operations
     */
    public ScheduleController(DoctorController doctorController, PatientController patientController) {
        this.data = new ScheduleDAO();
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.doctorScheduleView = new DoctorScheduleView();
        this.doctorAvailabilityView = new DoctorAvailabilityView(this);
        this.patientScheduleView = new PatientScheduleView();
        this.patientBookScheduleView = new PatientBookScheduleView(this);
        this.patientReScheduleView = new PatientReScheduleView(this);
        this.patientCancelView = new PatientCancelView(this);
    }

    /**
     * Displays the schedule for a specific doctor.
     *
     * @param doctorId the ID of the doctor whose schedule is to be shown
     */
    public void showDoctorSchedule(String doctorId) {
        Schedule schedule = data.find(doctorId);
        if (schedule == null) {
            System.out.println("----------------------------------------------");
            System.out.println("No schedule found for Doctor ID: " + doctorId);
            System.out.println("Please set your schedule~~~");
            System.out.println("----------------------------------------------");
            return;
        }
        List<Appointment> allAppointments = doctorController.getAppointmentsById(doctorId);
        HashMap<LocalDate, Appointment> Appointments = new HashMap<>();
        for (LocalDate date : schedule.getDateAvailability().keySet()) {
            for (Appointment appointment : allAppointments) {
                if (appointment.getDate().equals(date.format(DATE_FORMAT)) && appointment.getStatus().equals("approved")) {
                    Appointments.put(date, appointment);
                    break;
                }
            }
        }
        // Pass consolidated data to the view
        doctorScheduleView.menu(doctorId, schedule.getDateAvailability(), schedule.getDateTime(), Appointments);
    }

    /**
     * Navigates to the set availability view for a specific doctor.
     *
     * @param doctorId the ID of the doctor
     */
    public void showSetAvailabilityView(String doctorId) {
        doctorAvailabilityView.menu(doctorId);
    }

    /**
     * Updates the schedule for a specific doctor.
     *
     * @param doctorId   the ID of the doctor
     * @param date       the date to update the schedule
     * @param isAvailable whether the doctor is available on the specified date
     * @param time       the available time for appointments
     * @param doctorUse  indicates if the update is made by the doctor (true) or system (false)
     */
    public void updateDoctorSchedule(String doctorId, LocalDate date, Boolean isAvailable, String time, Boolean doctorUse) {
        if (data.find(doctorId) == null) {
            Schedule newSchedule = new Schedule(doctorId);
            newSchedule.getDateAvailability().put(date, isAvailable);
            newSchedule.getDateTime().put(date, time);
            data.add(newSchedule);
        } else {
            Boolean validAppt = false;

            for (Appointment a : doctorController.getConfirmedAppointmentsById(doctorId)) {
                if (a.getDate().equals(date.format(DATE_FORMAT))) { // if existing appointment
                    validAppt = true;
                }
            }

            if (!doctorUse) validAppt = false;

            if (validAppt) {
                System.out.println("You have an appointment on that date! Unable to set availability");
            } else {
                data.updateIsAvailable(doctorId, date, isAvailable, time);
            }
        }
    }

    /**
     * Displays the schedule for all available doctors.
     */
    public void patientScheduleView() {
        patientScheduleView.menu(findAllAvailableDoctors());
    }

    /**
     * Displays the schedule booking view for a specific patient.
     *
     * @param patientId the ID of the patient
     */
    public void patientBookScheduleView(String patientId) {
        patientBookScheduleView.menu(findAllAvailableDoctors(), patientId);
    }

    /**
     * Displays the reschedule view for a specific patient.
     *
     * @param patientId the ID of the patient
     */
    public void patientReScheduleView(String patientId) {
        patientReScheduleView.menu(getAllConfirmedAppointments(), patientId);
    }

    /**
     * Displays the cancel appointment view for a specific patient.
     *
     * @param patientId the ID of the patient
     */
    public void patientCancelView(String patientId) {
        patientCancelView.menu(patientController.getConfirmedAppointmentsByPatientId(), patientId);
    }

    /**
     * Finds all available doctors based on their schedules.
     *
     * @return a list of available schedules
     */
    public List<Schedule> findAllAvailableDoctors() {
        List<Schedule> scheduleList = new ArrayList<>();
        for (Schedule s : data.fetch()) {
            if (s.getDateAvailability().containsValue(true)) {
                scheduleList.add(s);
            }
        }
        return scheduleList;
    }

    /**
     * Creates a new appointment request for a specific doctor.
     *
     * @param doctorId the ID of the doctor
     * @param date     the date of the appointment
     * @param time     the time of the appointment
     */
    public void createAppointmentRequest(String doctorId, String date, String time) {
        patientController.createAppointment(doctorId, date, time);
    }

    /**
     * Cancels an existing appointment and updates the doctor's schedule.
     *
     * @param appointmentId the ID of the appointment to be canceled
     * @param doctorId      the ID of the doctor
     * @param date          the date of the appointment
     * @param time          the time of the appointment
     */
    public void cancelAppointment(String appointmentId, String doctorId, String date, String time) {
        patientController.cancelAppointmentHandler(appointmentId);
        updateDoctorSchedule(doctorId, LocalDate.parse(date, DATE_FORMAT), true, time, false);
    }

    /**
     * Retrieves all confirmed appointments for the current patient.
     *
     * @return a list of confirmed appointments for the patient
     */
    public List<Appointment> getAllConfirmedAppointments() {
        return patientController.getConfirmedAppointmentsByPatientId();
    }

    /**
     * Reschedules an existing appointment and updates the doctor's schedule.
     *
     * @param appointmentId the ID of the appointment to be rescheduled
     * @param doctorId      the ID of the doctor
     * @param oldDate       the old date of the appointment
     * @param oldTime       the old time of the appointment
     * @param newDate       the new date for the appointment
     * @param newTime       the new time for the appointment
     */
    public void rescheduleAppointment(String appointmentId, String doctorId, String oldDate, String oldTime, String newDate, String newTime) {
        patientController.rescheduleAppointment(appointmentId, newDate, newTime);
        updateDoctorSchedule(doctorId, LocalDate.parse(oldDate, DATE_FORMAT), true, oldTime, false);
    }
}

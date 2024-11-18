package Controller;

import Data.DataAccess.AppointmentDAO;
import Model.Shared.Appointment;
import View.Appointments.AppointmentRequestsView;
import View.Appointments.AppointmentView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The AppointmentController class handles all operations related to appointments,
 * including creating, updating, and viewing appointments for doctors and patients.
 */
public class AppointmentController {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private DoctorController doctorController;
    private AppointmentRequestsView appointmentRequestsView;
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private AppointmentView view = new AppointmentView();

    /**
     * Default constructor for AppointmentController.
     */
    public AppointmentController() {
    }

    /**
     * Constructor for AppointmentController with a DoctorController.
     * Initializes the doctorController and appointmentRequestsView.
     *
     * @param doctorController the DoctorController instance to be used
     */
    public AppointmentController(DoctorController doctorController) {
        this.doctorController = doctorController;
        this.appointmentRequestsView = new AppointmentRequestsView(this);
    }

    /**
     * Creates a new appointment and saves it in the system.
     *
     * @param doctorId  the ID of the doctor for the appointment
     * @param patientId the ID of the patient for the appointment
     * @param time      the time of the appointment
     * @param date      the date of the appointment in dd-MM-yyyy format
     */
    public void createAppointment(String doctorId, String patientId, String time, String date) {
        Appointment appointment = new Appointment(doctorId, patientId, time, date, createNewAppointmentID());
        appointmentDAO.save(appointment);
        System.out.println("Appointment created successfully.");
    }

    /**
     * Creates a new unique appointment ID based on the existing appointments in the system.
     *
     * @return a new unique appointment ID
     */
    public String createNewAppointmentID() {
        List<Appointment> appointments = appointmentDAO.loadAll();
        int length = appointments.size();
        if (length == 0) {
            return "AP0001";
        }
        int last_index = length - 1;
        String largestIdString = appointments.get(last_index).getAppointmentID();
        int newIdInt = Integer.parseInt(largestIdString.substring(2)) + 1;

        return String.format("AP%04d", newIdInt);
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param appointmentId the ID of the appointment to retrieve
     * @return the Appointment object with the given ID
     */
    public Appointment getAppointment(String appointmentId) {
        return appointmentDAO.find(appointmentId, null);
    }

    /**
     * Retrieves all appointments from the system.
     *
     * @return a list of all appointments
     */
    public List<Appointment> getAllAppointment() {
        List<Appointment> appointments = appointmentDAO.loadAll();
        return appointments;
    }

    /**
     * Retrieves all appointments for a particular doctor, including completed and cancelled ones.
     *
     * @param docId the doctor's ID
     * @return a list of appointments associated with the given doctor ID
     */
    public List<Appointment> getAppointmentsByDoctorID(String docId) {
        return appointmentDAO.getAppointmentsByDocID(docId);
    }

    /**
     * Retrieves all approved appointments for a particular doctor.
     *
     * @param docId the doctor's ID
     * @return a list of approved appointments associated with the given doctor ID
     */
    public List<Appointment> getApprovedAppointmentsByDoctorID(String docId) {
        return appointmentDAO.getApprovedAppointmentsByDocID(docId);
    }

    /**
     * Retrieves all pending appointments for a particular doctor.
     *
     * @param docId the doctor's ID
     * @return a list of pending appointments associated with the given doctor ID
     */
    public List<Appointment> getPendingAppointmentsByDoctorID(String docId) {
        return appointmentDAO.getPendingAppointmentsByDocID(docId);
    }

    /**
     * Retrieves all pending appointments for a particular doctor on a specific date.
     *
     * @param docId the doctor's ID
     * @param date  the date of the appointments in dd-MM-yyyy format
     * @return a list of pending appointments for the given doctor ID on the specified date
     */
    public List<Appointment> getPendingAppointmentsByDoctorIDandDate(String docId, String date) {
        return appointmentDAO.getPendingAppointmentsByDocIDandDate(docId, date);
    }

    /**
     * Retrieves all appointments for a particular patient, including completed and cancelled ones.
     *
     * @param patientId the patient's ID
     * @return a list of appointments associated with the given patient ID
     */
    public List<Appointment> getAppointmentsByPatientID(String patientId) {
        return appointmentDAO.getAppointmentsByPatientID(patientId);
    }

    /**
     * Retrieves all approved appointments for a particular patient.
     *
     * @param patientId the patient's ID
     * @return a list of approved appointments associated with the given patient ID
     */
    public List<Appointment> getApprovedAppointmentsByPatientID(String patientId) {
        return appointmentDAO.getApprovedAppointmentsByPatientID(patientId);
    }

    /**
     * Updates the status of an appointment.
     *
     * @param appointmentId the ID of the appointment to update
     * @param newStatus     the new status of the appointment
     */
    public void updateAppointmentStatus(String appointmentId, String newStatus) {
        Appointment appointment = appointmentDAO.find(appointmentId, null);
        appointment.setStatus(newStatus);
        appointmentDAO.save(appointment);
        System.out.println("Appointment status updated successfully.");
    }

    /**
     * Updates an appointment to reschedule it.
     *
     * @param appointmentId the ID of the appointment to reschedule
     * @param newDate       the new date for the appointment
     * @param newTime       the new time for the appointment
     */
    public void updateAppointmentReschedule(String appointmentId, String newDate, String newTime) {
        Appointment appointment = appointmentDAO.find(appointmentId, null);
        appointment.setDate(newDate);
        appointment.setTime(newTime);
        appointment.setStatus("pending");
        appointmentDAO.save(appointment);
        System.out.println("Appointment reschedule request is made successfully. Awaiting doctor's approval.");
    }

    /**
     * Views a specific appointment by its ID.
     *
     * @param appointmentId the ID of the appointment to view
     */
    public void viewAppointment(String appointmentId) {
        Appointment appointment = appointmentDAO.find(appointmentId, null);
        System.out.println(" ");
        printAppointment(appointment);
        System.out.println(" ");
    }

    /**
     * Views all appointments in the system.
     */
    public void viewAllAppointment() {
        List<Appointment> appointments = appointmentDAO.loadAll();
        System.out.println("Displaying all appointments:");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    /**
     * Views all appointments for a particular doctor, including completed and cancelled ones.
     *
     * @param docId the doctor's ID
     */
    public void viewAppointmentsByDoctorID(String docId) {
        List<Appointment> appointments = appointmentDAO.getAppointmentsByDocID(docId);
        System.out.println("Displaying all appointments for the Doctor ID of " + docId + " : ");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    /**
     * Views all approved appointments for a particular doctor.
     *
     * @param docId the doctor's ID
     */
    public void viewApprovedAppointmentsByDoctorID(String docId) {
        List<Appointment> appointments = appointmentDAO.getApprovedAppointmentsByDocID(docId);
        if (appointments.isEmpty()) {
            System.out.println("There is no approved appointment for Doctor ID " + docId + ".");
            return;
        }
        System.out.println("Displaying all approved appointments for the Doctor ID of " + docId + " : ");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    /**
     * Views all pending appointments for a particular doctor.
     *
     * @param docId the doctor's ID
     */
    public void viewPendingAppointmentsByDoctorID(String docId) {
        List<Appointment> appointments = appointmentDAO.getPendingAppointmentsByDocID(docId);
        System.out.println("Displaying all pending appointments for the Doctor ID of " + docId + " : ");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    /**
     * Views all appointments for a particular patient, including completed and cancelled ones.
     *
     * @param patientId the patient's ID
     */
    public void viewAppointmentsByPatientID(String patientId) {
        List<Appointment> appointments = appointmentDAO.getAppointmentsByPatientID(patientId);
        System.out.println("Displaying all appointments for the Patient ID of " + patientId + " : ");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    /**
     * Views all approved appointments for a particular patient.
     *
     * @param patientId the patient's ID
     */
    public void viewApprovedAppointmentsByPatientID(String patientId) {
        List<Appointment> appointments = appointmentDAO.getApprovedAppointmentsByPatientID(patientId);
        System.out.println("Displaying all approved appointments for the Patient ID of " + patientId + " : ");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    /**
     * Prints the details of a given appointment.
     *
     * @param apt the Appointment object to be printed
     */
    public void printAppointment(Appointment apt) {
        this.view.printAppointment(apt.getAppointmentID(), apt.getDocID(), apt.getPatientID(), apt.getStatus(), apt.getTime(), apt.getDate());
    }

    /**
     * Navigates to the appointment requests view for a specific doctor.
     *
     * @param doctorId the doctor's ID
     */
    public void appointmentRequestsView(String doctorId) {
        appointmentRequestsView.menu(doctorId);
    }

    /**
     * Updates the schedule for an appointment and manages doctor's availability accordingly.
     *
     * @param appointmentId the appointment ID to update
     * @param doctorId      the doctor's ID for the appointment
     * @param date          the date of the appointment in dd-MM-yyyy format
     * @param time          the time of the appointment
     * @param approve       a flag indicating whether the appointment is approved
     */
    public void updateAppointmentSchedule(String appointmentId, String doctorId, String date, String time, Boolean approve) {
        if (approve) {
            doctorController.updateDoctorSchedule(doctorId, LocalDate.parse(date, DATE_FORMAT), false, time);
            updateAppointmentStatus(appointmentId, "approved");
            for (Appointment a : getPendingAppointmentsByDoctorIDandDate(doctorId, date)) {
                updateAppointmentStatus(a.getAppointmentID(), "cancelled");
            }
        } else {
            updateAppointmentStatus(appointmentId, "cancelled");
        }
    }
}

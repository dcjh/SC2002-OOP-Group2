package Controller;
import Data.DataAccess.AppointmentDAO;
import Model.Shared.Appointment;
import View.Appointments.AppointmentRequestsView;
import View.Appointments.AppointmentView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppointmentController { 
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private DoctorController doctorController;
    private AppointmentRequestsView appointmentRequestsView;
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private AppointmentView view = new AppointmentView();
    

    public AppointmentController() {
    }

    public AppointmentController(DoctorController doctorController) {
        this.doctorController = doctorController;
        this.appointmentRequestsView = new AppointmentRequestsView(this);
    }

    // create

    // Create a new appointment
    public void createAppointment(String doctorId, String patientId, String time, String date) {
        Appointment appointment = new Appointment(doctorId, patientId, time, date, createNewAppointmentID());
        appointmentDAO.save(appointment);
        System.out.println("Appointment created successfully.");
    }

    public String createNewAppointmentID(){
        List<Appointment> appointments = appointmentDAO.loadAll();
        int length = appointments.size();
        if(length==0){
            return "AP0001";
        }
        int last_index = length - 1;
        String largestIdString = appointments.get(last_index).getAppointmentID();
        int newIdInt = Integer.parseInt(largestIdString.substring(2)) + 1;

        return String.format("AP%04d", newIdInt);
    }

    // getter methods

    // Get an appointment by ID
    public Appointment getAppointment(String appointmentId) {
        return appointmentDAO.find(appointmentId, null);
    }

    // Get all appointments
    public List<Appointment> getAllAppointment() {
        List<Appointment> appointments = appointmentDAO.loadAll();
        return appointments;
    }

    // Get all appointments for a particular DoctorID (completed and cancelled included)
    public List<Appointment> getAppointmentsByDoctorID(String docId) {
        return appointmentDAO.getAppointmentsByDocID(docId);
    }

    // Get all approved appointments for a particular DoctorID
    public List<Appointment> getApprovedAppointmentsByDoctorID(String docId) {
        return appointmentDAO.getApprovedAppointmentsByDocID(docId);
    }

    // Get all pending appointments for a particular DoctorID
    public List<Appointment> getPendingAppointmentsByDoctorID(String docId) {
        return appointmentDAO.getPendingAppointmentsByDocID(docId);
    }
    public List<Appointment> getPendingAppointmentsByDoctorIDandDate(String docId, String date) {
        return appointmentDAO.getPendingAppointmentsByDocIDandDate(docId, date);
    }

    // Get all appointments for a particular PatientID (completed and cancelled included)
    public List<Appointment> getAppointmentsByPatientID(String patientId) {
        return appointmentDAO.getAppointmentsByPatientID(patientId);
    }

    // Get all approved appointments for a particular PatientID
    public List<Appointment> getApprovedAppointmentsByPatientID(String patientId) {
        return appointmentDAO.getApprovedAppointmentsByPatientID(patientId);
    }

    // setter functions
    // for status (there are 4 status [pending, cancelled, approved, completed])

    // Update an appointment's status
    public void updateAppointmentStatus(String appointmentId, String newStatus) {
        Appointment appointment = appointmentDAO.find(appointmentId, null);
        appointment.setStatus(newStatus);
        appointmentDAO.save(appointment);
        System.out.println("Appointment status updated successfully.");
    }

    // for patient to reschedule
    public void updateAppointmentReschedule(String appointmentId, String newDate, String newTime) {
        Appointment appointment = appointmentDAO.find(appointmentId, null);
        appointment.setDate(newDate);
        appointment.setTime(newTime);
        appointment.setStatus("pending");
        appointmentDAO.save(appointment);
        System.out.println("Appointment reschedule request is made successfully. Awaiting doctor's approval.");
    }

    // no setter method for patientID and doctorID because no one can not modify the patientID or doctorID

    // view method

    // View an appointment by ID
    public void viewAppointment(String appointmentId) {
        Appointment appointment = appointmentDAO.find(appointmentId, null);
        System.out.println(" ");
        printAppointment(appointment);
        System.out.println(" ");
    }

    // View all appointments for admin
    public void viewAllAppointment() {
        List<Appointment> appointments = appointmentDAO.loadAll();
        System.out.println("Displaying all appointments:");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    // View all appointments for a particular DoctorID (completed and cancelled included)
    public void viewAppointmentsByDoctorID(String docId) {
        List<Appointment> appointments = appointmentDAO.getAppointmentsByDocID(docId);
        System.out.println("Displaying all appointments for the Doctor ID of " + docId + " : ");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    // View all approved appointments for a particular DoctorID
    public void viewApprovedAppointmentsByDoctorID(String docId) {
        List<Appointment> appointments = appointmentDAO.getApprovedAppointmentsByDocID(docId);
        if(appointments.isEmpty()){
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

    // View all pending appointments for a particular DoctorID
    public void viewPendingAppointmentsByDoctorID(String docId) {
        List<Appointment> appointments = appointmentDAO.getPendingAppointmentsByDocID(docId);
        System.out.println("Displaying all pending appointments for the Doctor ID of " + docId + " : ");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    // View all appointments for a particular PatientID (completed and cancelled included)
    public void viewAppointmentsByPatientID(String patientId) {
        List<Appointment> appointments = appointmentDAO.getAppointmentsByPatientID(patientId);
        System.out.println("Displaying all appointments for the Patient ID of " + patientId + " : ");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    // View all approved appointments for a particular PatientID
    public void viewApprovedAppointmentsByPatientID(String patientId) {
        List<Appointment> appointments = appointmentDAO.getApprovedAppointmentsByPatientID(patientId);
        System.out.println("Displaying all approved appointments for the Patient ID of " + patientId + " : ");
        System.out.println(" ");
        for (Appointment a : appointments) {
            printAppointment(a);
            System.out.println(" ");
        }
    }

    public void printAppointment(Appointment apt){
        this.view.printAppointment(apt.getAppointmentID(), apt.getDocID(), apt.getPatientID(), apt.getStatus(), apt.getTime(), apt.getDate());
    }

    //navigate to apointment requests view
    public void appointmentRequestsView(String doctorId) {
        appointmentRequestsView.menu(doctorId);
    }

    //to update doctor's availability
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

    public void appointmentManagementView (String patientId) {

    }
    
}


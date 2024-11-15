package Controller;
import Data.DataAccess.AppointmentDAO;
import Model.Shared.Appointment;
import View.AppointmentView;
import java.util.List;

public class AppointmentController { 
    private DoctorController doctorController;

    AppointmentDAO appointmentDAO = new AppointmentDAO();
    AppointmentView view = new AppointmentView();

    public AppointmentController(DoctorController doctorController) {
        this.doctorController = doctorController;
    }

    // Create a new appointment
    public void createAppointment(String doctorId, String patientId, String time, String date) {
        Appointment appointment = new Appointment(doctorId, patientId, time, date);
        appointmentDAO.save(appointment);
        System.out.println("Appointment created successfully.");
    }

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

    // Return all appointments
    public List<Appointment> getAllAppointment() {
        List<Appointment> appointments = appointmentDAO.loadAll();
        return appointments;
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

    // view

    public void printAppointment(Appointment apt){
        this.view.printAppointment(apt.getAppointmentID(), apt.getDocID(), apt.getPatientID(), apt.getStatus(), apt.getTime(), apt.getDate());
    }

}

package Controller;
import Data.DataAccess.AppointmentOutcomeDAO;
import Model.Shared.Appointment;
import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import View.AppointmentOutcomeView;
import java.util.ArrayList;
import java.util.List;

public class AppointmentOutcomeController {
    private DoctorController doctorController;
    private AppointmentController appointmentController;
    private AppointmentOutcomeDAO dao = new AppointmentOutcomeDAO();
    private AppointmentOutcomeView view;

    public AppointmentOutcomeController() {
    }

    public AppointmentOutcomeController(DoctorController doctorController, AppointmentController appointmentController) {
        this.doctorController = doctorController;
        this.appointmentController = appointmentController;
        this.view = new AppointmentOutcomeView(this);
    }

    // create

    public void createAppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> medications, String notes, String doctorID, String patientID, String appointmentID) {
        AppointmentOutcome outcome = new AppointmentOutcome(date, time, typeOfService, medications, notes, doctorID, patientID, appointmentID);
        dao.save(outcome);
    }

    // getter

    // Get all appointment outcomes 
    public List<AppointmentOutcome> getAppointmentOutcomes() {
        return dao.loadAll();
    }

    // Get Appointment from AppointmentID
    public Appointment getAppointment(String appointmentId){
        return appointmentController.getAppointment(appointmentId);
    }
    
    // Get all appointment outcomes for a particular DoctorID
    public List<AppointmentOutcome> getAppointmentOutcomeByDoctorID(String docId) {
        return dao.getAppointmentOutcomeByDoctorID(docId);
    }

    // Get all appointment outcomes for a particular PatientID
    public List<AppointmentOutcome> getAppointmentOutcomeByPatientID(String patientId) {
        return dao.getAppointmentOutcomeByPatientID(patientId);
    }

    // setter method
    
    // change the status of the medicine

    public void setStatusDispensed(String appointmentOutcomeId, String medicineName){
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setStatusDispensed(medicineName);
        dao.save(outcome);
        // System.out.println(medicineName + " is dispensed successfully for Appointment Outcome " + appointmentOutcomeId + "."); remove this to view
    }

    // change the consultation notes

    public void setConsultationNotes(String appointmentOutcomeId, String consultationNotes){
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setConsultationNotes(consultationNotes);
        dao.save(outcome);
    }

    // printing Appointment Outcome record 

    public void printAppointmentOutcome(AppointmentOutcome outcome){
        this.view.printAppointmentOutcome(outcome.getAppointmentOutcomeID(), outcome.getDoctorID(), outcome.getPatientID(), outcome.getAppointmentID(), outcome.getDate(), outcome.getTime(), outcome.getTypeOfService(), outcome.getPrescribedMedications(), outcome.getConsultationNotes());
    }

    // View appointments by doctorId

    public void viewAppointmentsByDoctorID(String doctorID){
        appointmentController.viewApprovedAppointmentsByDoctorID(doctorID);
    }

    public void viewMenuDoctor(String doctorId){
        view.menuDoctor(doctorId);
    }
    
}

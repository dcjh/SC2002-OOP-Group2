package Controller;
import Data.DataAccess.AppointmentOutcomeDAO;
import Model.Shared.Appointment;
import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import View.Appointments.AppointmentOutcomeView;
import java.util.ArrayList;
import java.util.List;

public class AppointmentOutcomeController {
    // private DoctorController doctorController;
    private AppointmentController appointmentController;
    private AppointmentOutcomeDAO dao = new AppointmentOutcomeDAO();
    private AppointmentOutcomeView view;

    public AppointmentOutcomeController() {
    }

    public AppointmentOutcomeController(/*DoctorController doctorController,*/ AppointmentController appointmentController) {
        // this.doctorController = doctorController;
        this.appointmentController = appointmentController;
        this.view = new AppointmentOutcomeView(this);
    }

    
    /** 
     * @param date
     * @param time
     * @param typeOfService
     * @param medications
     * @param notes
     * @param doctorID
     * @param patientID
     * @param appointmentID
     */
    // create

    public void createAppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> medications, String notes, String doctorID, String patientID, String appointmentID) {
        AppointmentOutcome outcome = new AppointmentOutcome(date, time, typeOfService, medications, notes, doctorID, patientID, appointmentID, createNewAppointmentOutcomeID());
        if(appointmentID != "-"){
            appointmentController.updateAppointmentStatus(appointmentID, "completed");
        }
        dao.save(outcome);
    }

    
    /** 
     * @return String
     */
    public String createNewAppointmentOutcomeID(){
        List<AppointmentOutcome> outcomes = dao.loadAll();
        int length = outcomes.size();
        if(length==0){
            return "AO0001";
        }
        int last_index = length - 1;
        String largestIdString = outcomes.get(last_index).getAppointmentOutcomeID();
        int newIdInt = Integer.parseInt(largestIdString.substring(2)) + 1;

        return String.format("AO%04d", newIdInt);
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

    // Get Appointment List from DoctorID
    public List<Appointment> getApprovedAppointmentsByDoctorID(String doctorId){
        return appointmentController.getApprovedAppointmentsByDoctorID(doctorId);
    }
    
    // Get all appointment outcomes for a particular DoctorID
    public List<AppointmentOutcome> getAppointmentOutcomeByDoctorID(String docId) {
        return dao.getAppointmentOutcomeByDoctorID(docId);
    }

    // Get appointment outcome by appointmentID
    public AppointmentOutcome getAppointmentOutcomeByAppointmentID(String appointmentId) {
        List<AppointmentOutcome> outcomes = dao.loadAll();
        for (AppointmentOutcome o:outcomes){
            if(o.getAppointmentID().equals(appointmentId)){
                return o;
            }
        }
        return null;
    }


    // Get all appointment outcomes for a particular PatientID
    public List<AppointmentOutcome> getAppointmentOutcomeByPatientID(String patientId) {
        return dao.getAppointmentOutcomeByPatientID(patientId);
    }

    // setter method
    
    // change the status of the medicine

    // public void setStatusDispensed(String appointmentOutcomeId, String medicineName){
    //     AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
    //     outcome.setStatusDispensed(medicineName);
    //     dao.save(outcome);
        // System.out.println(medicineName + " is dispensed successfully for Appointment Outcome " + appointmentOutcomeId + "."); remove this to view
    // }

    public void setPrescribedMedicineUpdatedStatus(String appointmentOutcomeId, ArrayList<PrescribedMedication> newMedications){
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setPrescribedMedication(newMedications);
        dao.save(outcome);
    }

    // change the consultation notes

    public void setConsultationNotes(String appointmentOutcomeId, String consultationNotes){
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setConsultationNotes(consultationNotes);
        dao.save(outcome);
    }

    public void setTypeOfService(String appointmentOutcomeId, String consultationNotes){
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setConsultationNotes(consultationNotes);
        dao.save(outcome);
    }

    public void addMedication(String appointmentOutcomeId, String consultationNotes){
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setConsultationNotes(consultationNotes);
        dao.save(outcome);
    }

    public void removeMedication(String appointmentOutcomeId, String consultationNotes){
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

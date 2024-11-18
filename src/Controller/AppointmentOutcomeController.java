package Controller;

import Data.DataAccess.AppointmentOutcomeDAO;
import Model.Shared.Appointment;
import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import View.Appointments.AppointmentOutcomeView;
import java.util.ArrayList;
import java.util.List;

/**
 * The AppointmentOutcomeController class is responsible for managing appointment outcomes.
 * This includes creating, updating, and retrieving appointment outcomes for doctors and patients.
 */
public class AppointmentOutcomeController {

    // private DoctorController doctorController; // Unused DoctorController instance (commented out)
    private AppointmentController appointmentController;
    private AppointmentOutcomeDAO dao = new AppointmentOutcomeDAO();
    private AppointmentOutcomeView view = new AppointmentOutcomeView(this);

    /**
     * Default constructor for AppointmentOutcomeController.
     */
    public AppointmentOutcomeController() {
    }

    /**
     * Constructor for AppointmentOutcomeController with an AppointmentController instance.
     *
     * @param appointmentController the AppointmentController instance to manage appointments
     */
    public AppointmentOutcomeController(/*DoctorController doctorController,*/ AppointmentController appointmentController) {
        // this.doctorController = doctorController; // Currently not used
        this.appointmentController = appointmentController;
    }

    /**
     * Creates a new appointment outcome and saves it in the system.
     * If an appointment ID is provided, updates the appointment status to "completed".
     *
     * @param date          the date of the appointment outcome
     * @param time          the time of the appointment outcome
     * @param typeOfService the type of service provided
     * @param medications   a list of prescribed medications
     * @param notes         consultation notes
     * @param doctorID      the doctor's ID
     * @param patientID     the patient's ID
     * @param appointmentID the appointment ID (can be "-" if not linked to an appointment)
     */
    public void createAppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> medications, String notes, String doctorID, String patientID, String appointmentID) {
        AppointmentOutcome outcome = new AppointmentOutcome(date, time, typeOfService, medications, notes, doctorID, patientID, appointmentID, createNewAppointmentOutcomeID());
        if (appointmentID != "-") {
            appointmentController.updateAppointmentStatus(appointmentID, "completed");
        }
        dao.save(outcome);
    }

    /**
     * Creates a new unique appointment outcome ID.
     *
     * @return the new unique appointment outcome ID
     */
    public String createNewAppointmentOutcomeID() {
        List<AppointmentOutcome> outcomes = dao.loadAll();
        int length = outcomes.size();
        if (length == 0) {
            return "AO0001";
        }
        int last_index = length - 1;
        String largestIdString = outcomes.get(last_index).getAppointmentOutcomeID();
        int newIdInt = Integer.parseInt(largestIdString.substring(2)) + 1;

        return String.format("AO%04d", newIdInt);
    }

    /**
     * Retrieves all appointment outcomes.
     *
     * @return a list of all appointment outcomes
     */
    public List<AppointmentOutcome> getAppointmentOutcomes() {
        return dao.loadAll();
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param appointmentId the ID of the appointment to retrieve
     * @return the Appointment object with the given ID
     */
    public Appointment getAppointment(String appointmentId) {
        return appointmentController.getAppointment(appointmentId);
    }

    /**
     * Retrieves a list of approved appointments for a specific doctor.
     *
     * @param doctorId the doctor's ID
     * @return a list of approved appointments for the specified doctor
     */
    public List<Appointment> getApprovedAppointmentsByDoctorID(String doctorId) {
        return appointmentController.getApprovedAppointmentsByDoctorID(doctorId);
    }

    /**
     * Retrieves all appointment outcomes for a specific doctor.
     *
     * @param docId the doctor's ID
     * @return a list of appointment outcomes for the specified doctor
     */
    public List<AppointmentOutcome> getAppointmentOutcomeByDoctorID(String docId) {
        return dao.getAppointmentOutcomeByDoctorID(docId);
    }

    /**
     * Retrieves the appointment outcome based on the appointment ID.
     *
     * @param appointmentId The ID of the appointment to retrieve the outcome for.
     * @return The appointment outcome corresponding to the given appointment ID, or null if no match is found.
     *
     * This method iterates through all appointment outcomes and returns the one that matches the specified appointment ID.
     */
    public AppointmentOutcome getAppointmentOutcomeByAppointmentID(String appointmentId) {
        List<AppointmentOutcome> outcomes = dao.loadAll();
        for (AppointmentOutcome o : outcomes) {
            if (o.getAppointmentID().equals(appointmentId)) {
                return o;
            }
        }
        return null;
    }
  
    /**
     * Retrieves all appointment outcomes for a specific patient.
     *
     * @param patientId the patient's ID
     * @return a list of appointment outcomes for the specified patient
     */
    public List<AppointmentOutcome> getAppointmentOutcomeByPatientID(String patientId) {
        return dao.getAppointmentOutcomeByPatientID(patientId);
    }


    /**
     * Updates the prescribed medications for a specific appointment outcome.
     *
     * @param appointmentOutcomeId The ID of the appointment outcome to update.
     * @param newMedications       A list of new prescribed medications to set for the appointment outcome.
     *
     * This method retrieves the appointment outcome using the specified ID, updates the prescribed medications,
     * and saves the updated outcome back to the data source.
     */
    public void setPrescribedMedicineUpdatedStatus(String appointmentOutcomeId, ArrayList<PrescribedMedication> newMedications) {
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setPrescribedMedication(newMedications);
        dao.save(outcome);
    }

    /**
     * Updates the consultation notes for a specific appointment outcome.
     *
     * @param appointmentOutcomeId the ID of the appointment outcome
     * @param consultationNotes    the new consultation notes
     */
    public void setConsultationNotes(String appointmentOutcomeId, String consultationNotes) {
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setConsultationNotes(consultationNotes);
        dao.save(outcome);
    }

    /**
     * Sets the type of service for a specific appointment outcome by updating the consultation notes.
     *
     * @param appointmentOutcomeId the ID of the appointment outcome
     * @param consultationNotes    the consultation notes to be set (used here as type of service)
     */
    public void setTypeOfService(String appointmentOutcomeId, String consultationNotes) {
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setConsultationNotes(consultationNotes); // Kept as it was in your original code
        dao.save(outcome);
    }

    /**
     * Adds a medication to a specific appointment outcome by updating the consultation notes.
     *
     * @param appointmentOutcomeId the ID of the appointment outcome
     * @param consultationNotes    the consultation notes to be updated
     */
    public void addMedication(String appointmentOutcomeId, String consultationNotes) {
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setConsultationNotes(consultationNotes);
        dao.save(outcome);
    }

    /**
     * Removes a medication from a specific appointment outcome by updating the consultation notes.
     *
     * @param appointmentOutcomeId the ID of the appointment outcome
     * @param consultationNotes    the consultation notes to be updated
     */
    public void removeMedication(String appointmentOutcomeId, String consultationNotes) {
        AppointmentOutcome outcome = dao.find(appointmentOutcomeId);
        outcome.setConsultationNotes(consultationNotes);
        dao.save(outcome);
    }

    /**
     * Prints the details of a given appointment outcome.
     *
     * @param outcome the AppointmentOutcome object to print
     */
    public void printAppointmentOutcome(AppointmentOutcome outcome) {
        this.view.printAppointmentOutcome(outcome.getAppointmentOutcomeID(), outcome.getDoctorID(), outcome.getPatientID(), outcome.getAppointmentID(), outcome.getDate(), outcome.getTime(), outcome.getTypeOfService(), outcome.getPrescribedMedications(), outcome.getConsultationNotes());
    }

    /**
     * Views the approved appointments for a specific doctor.
     *
     * @param doctorID the doctor's ID
     */
    public void viewAppointmentsByDoctorID(String doctorID) {
        appointmentController.viewApprovedAppointmentsByDoctorID(doctorID);
    }

    /**
     * Displays the menu for appointment outcomes for a specific doctor.
     *
     * @param doctorId the doctor's ID
     */
    public void viewMenuDoctor(String doctorId) {
        view.menuDoctor(doctorId);
    }
}

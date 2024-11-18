package Controller;

import java.util.List;
import Data.DataAccess.PastDiagnosesAndTreatmentsDAO;
import Model.Shared.PastDiagnosesAndTreatments;
import View.MedicalRecord.PastDiagnosesAndTreatmentsView;

/**
 * The PastDiagnosesAndTreatmentsController class manages past diagnoses and treatments for patients.
 * It provides methods to retrieve, view, and add diagnoses and treatments for a specific patient.
 */
public class PastDiagnosesAndTreatmentsController {
    private PastDiagnosesAndTreatmentsDAO dao;
    private PastDiagnosesAndTreatmentsView view;

    /**
     * Constructs a PastDiagnosesAndTreatmentsController instance.
     * Initializes the DAO and view components.
     */
    public PastDiagnosesAndTreatmentsController() {
        this.dao = new PastDiagnosesAndTreatmentsDAO();
        this.view = new PastDiagnosesAndTreatmentsView();
    }

    /**
     * Retrieves a list of past diagnoses and treatments for a specific patient.
     *
     * @param patientID the ID of the patient
     * @return a list of PastDiagnosesAndTreatments objects for the specified patient
     */
    public List<PastDiagnosesAndTreatments> getPastDiagnosesAndTreatments(String patientID) {
        return dao.getDiagnosesAndTreatmentsByPatientID(patientID);
    }

    /**
     * Displays all past diagnoses and treatments for a specific patient.
     *
     * @param patientID the ID of the patient whose past diagnoses and treatments are to be viewed
     */
    public void viewPastDiagnosesAndTreatments(String patientID) {
        List<PastDiagnosesAndTreatments> records = getPastDiagnosesAndTreatments(patientID);
        view.displayPastDiagnosesAndTreatments(patientID, records);
    }

    /**
     * Adds a new diagnosis and treatment record for a specific patient.
     *
     * @param patientID the ID of the patient
     * @param diagnosis the diagnosis to be added
     * @param treatment the treatment to be added
     */
    public void addPastDiagnosisAndTreatment(String patientID, String diagnosis, String treatment) {
        PastDiagnosesAndTreatments record = new PastDiagnosesAndTreatments(patientID, diagnosis, treatment);
        dao.addSingleRecord(record);
        System.out.println("Diagnosis and treatment added successfully for Patient ID: " + patientID);
    }
}

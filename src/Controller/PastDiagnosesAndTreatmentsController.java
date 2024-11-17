package Controller;

import java.util.List;
import Data.DataAccess.PastDiagnosesAndTreatmentsDAO;
import Model.Shared.PastDiagnosesAndTreatments;
import View.MedicalRecord.PastDiagnosesAndTreatmentsView;

public class PastDiagnosesAndTreatmentsController {
    private PastDiagnosesAndTreatmentsDAO dao;
    private PastDiagnosesAndTreatmentsView view;

    public PastDiagnosesAndTreatmentsController() {
        this.dao = new PastDiagnosesAndTreatmentsDAO();
        this.view = new PastDiagnosesAndTreatmentsView();
    }
    public List<PastDiagnosesAndTreatments> getPastDiagnosesAndTreatments(String patientID) {
        return dao.getDiagnosesAndTreatmentsByPatientID(patientID);

    }

    // view all past diagnoses and treatments  via patientid
    public void viewPastDiagnosesAndTreatments(String patientID) {
        List<PastDiagnosesAndTreatments> records = getPastDiagnosesAndTreatments(patientID);
        view.displayPastDiagnosesAndTreatments(patientID, records);
    }

    //add  diagnosis and treatment via patientid
    public void addPastDiagnosisAndTreatment(String patientID, String diagnosis, String treatment) {
        PastDiagnosesAndTreatments record = new PastDiagnosesAndTreatments(patientID, diagnosis, treatment);
        dao.addSingleRecord(record);
        System.out.println("Diagnosis and treatment added successfully for Patient ID: " + patientID);
    }
}
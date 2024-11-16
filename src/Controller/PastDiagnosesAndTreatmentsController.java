import java.util.List;
import Data.DataAccess.PastDiagnosesAndTreatmentsDAO;
import Model.Shared.PastDiagnosesAndTreatments;
import View.PastDiagnosesAndTreatmentsView;

public class PastDiagnosesAndTreatmentsController {
    private PastDiagnosesAndTreatmentsDAO dao;
    private PastDiagnosesAndTreatmentsView view;

    public PastDiagnosesAndTreatmentsController() {
        this.dao = new PastDiagnosesAndTreatmentsDAO();
        this.view = new PastDiagnosesAndTreatmentsView();
    }

    // view all past diagnoses and treatments  via patientid
    public void viewPastDiagnosesAndTreatments(String patientID) {
        List<PastDiagnosesAndTreatments> records = dao.loadAll(patientID);
        if (records.isEmpty()) {
            System.out.println("No past diagnoses and treatments found for Patient ID: " + patientID);
        } else {
            view.displayPastDiagnosesAndTreatments(patientID, records);
        }
    }

    //add  diagnosis and treatment via patientid
    public void addPastDiagnosisAndTreatment(String patientID, String diagnosis, String treatment) {
        PastDiagnosesAndTreatments record = new PastDiagnosesAndTreatments(diagnosis, treatment);
        dao.addSingleRecord(patientID, record);
        System.out.println("Diagnosis and treatment added successfully for Patient ID: " + patientID);
    }
}
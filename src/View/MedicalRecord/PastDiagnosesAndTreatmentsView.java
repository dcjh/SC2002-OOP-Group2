package View.MedicalRecord;

import java.util.List;
import Model.Shared.PastDiagnosesAndTreatments;

/**
 * Represents the view for displaying past diagnoses and treatments of a patient.
 */
public class PastDiagnosesAndTreatmentsView {

    /**
     * Displays all past diagnoses and treatments for a specific patient.
     * 
     * @param patientID The ID of the patient whose past records are to be displayed.
     * @param records A list of past diagnoses and treatments for the specified patient.
     */
    public void displayPastDiagnosesAndTreatments(String patientID, List<PastDiagnosesAndTreatments> records) {
        System.out.println("Past Diagnoses and Treatments for Patient ID: " + patientID);
        
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (int i = 0; i < records.size(); i++) {
            PastDiagnosesAndTreatments record = records.get(i);
            System.out.printf("%d. Diagnosis: %s, Treatment: %s%n", (i + 1), record.getDiagnosis(), record.getTreatment());
        }
    }
}

package View.MedicalRecord;

import java.util.List;
import Model.Shared.MedicalRecord;
import Model.Shared.PastDiagnosesAndTreatments;

/**
 * Represents the view for displaying a patient's medical record information.
 */
public class MedicalRecordView {

    /**
     * Displays the full medical record of a patient, including basic details and past diagnoses and treatments.
     * 
     * @param medicalRecord The medical record to display.
     */
    public void fullDisplay(MedicalRecord medicalRecord) {
        System.out.println("Medical Record:");
        System.out.println("Patient ID: " + medicalRecord.getPatientID());
        System.out.println("Name: " + medicalRecord.getName());
        System.out.println("Date of Birth: " + medicalRecord.getDob());
        System.out.println("Gender: " + medicalRecord.getGender());
        System.out.println("Phone Number: " + medicalRecord.getPhoneNumber());
        System.out.println("Email: " + medicalRecord.getEmail());
        System.out.println("Blood Type: " + medicalRecord.getBloodType());
        System.out.println("Allergies: " + medicalRecord.getAllergies());

        System.out.println("\nPast Diagnoses and Treatments:");
        List<PastDiagnosesAndTreatments> diagnoses = medicalRecord.getPastDiagnosesAndTreatments();

        if (diagnoses != null && !diagnoses.isEmpty()) {
            for (PastDiagnosesAndTreatments past : diagnoses) {
                System.out.println("Diagnosis: " + past.getDiagnosis());
                System.out.println("Treatment: " + past.getTreatment());
                System.out.println();
            }
        } else {
            System.out.println("No past diagnoses and treatments available.");
        }
    }
}

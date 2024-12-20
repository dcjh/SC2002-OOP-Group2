package Data.DataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Model.Shared.PastDiagnosesAndTreatments;

/**
 * The PastDiagnosesAndTreatmentsDAO class is responsible for managing data access
 * for past diagnoses and treatments. It provides methods for retrieving and adding records.
 */
public class PastDiagnosesAndTreatmentsDAO {
    private final String filePath = "src/Data/Assets/PastDiagnosesAndTreatments.csv";

    /**
     * Retrieves all diagnoses and treatments for a given patient ID.
     *
     * @param patientID The ID of the patient whose diagnoses and treatments are to be retrieved.
     * @return A list of PastDiagnosesAndTreatments objects representing the patient's records.
     */
    public List<PastDiagnosesAndTreatments> getDiagnosesAndTreatmentsByPatientID(String patientID) {
        List<PastDiagnosesAndTreatments> diagnosesAndTreatments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the header row
                    continue;
                }
                String[] fields = line.split(",");
                if (fields[0].equals(patientID)) { // Check if the patient ID matches
                    for (int i = 1; i < fields.length - 1; i += 2) {
                        String diagnosis = fields[i].trim();
                        String treatment = fields[i + 1].trim();
                        diagnosesAndTreatments.add(new PastDiagnosesAndTreatments(patientID, diagnosis, treatment));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading diagnoses and treatments: " + e.getMessage());
        }
        return diagnosesAndTreatments;
    }

    /**
     * Adds a single record of diagnosis and treatment for a patient.
     * Checks for duplicates before adding.
     *
     * @param treatment The PastDiagnosesAndTreatments object to be added.
     */
    public synchronized void addSingleRecord(PastDiagnosesAndTreatments treatment) {
        String newLine = treatment.getPatientID() + "," + treatment.getDiagnosis() + "," + treatment.getTreatment();

        // Check for duplicate before adding
        List<PastDiagnosesAndTreatments> existingRecords = getDiagnosesAndTreatmentsByPatientID(treatment.getPatientID());
        for (PastDiagnosesAndTreatments record : existingRecords) {
            if (record.getDiagnosis().equals(treatment.getDiagnosis()) &&
                    record.getTreatment().equals(treatment.getTreatment())) {
                System.out.println("Duplicate record. Skipping addition.");
                return;
            }
        }

        // Append the new record to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(newLine);
            bw.newLine();
            System.out.println("Record added successfully for Patient ID: " + treatment.getPatientID());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Retrieves all diagnoses and treatments for all patients.
     * This method is intended for administrative or debugging purposes.
     *
     * @return A list of PastDiagnosesAndTreatments objects representing all records.
     */
    public List<PastDiagnosesAndTreatments> getAllDiagnosesAndTreatments() {
        List<PastDiagnosesAndTreatments> allDiagnosesAndTreatments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the header row
                    continue;
                }
                String[] fields = line.split(",");
                String patientID = fields[0].trim();
                for (int i = 1; i < fields.length - 1; i += 2) {
                    String diagnosis = fields[i].trim();
                    String treatment = fields[i + 1].trim();
                    allDiagnosesAndTreatments.add(new PastDiagnosesAndTreatments(patientID, diagnosis, treatment));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading all diagnoses and treatments: " + e.getMessage());
        }
        return allDiagnosesAndTreatments;
    }
}

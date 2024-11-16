package Data.DataAccess;

import Model.Shared.PastDiagnosesAndTreatments;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PastDiagnosesAndTreatmentsDAO {
    private final String filePath = "src/Data/Assets/PastDiagnosesAndTreatments.csv";

    // Retrieve all diagnoses and treatments for a given patient ID
    public List<PastDiagnosesAndTreatments> getDiagnosesAndTreatmentsByPatientID(String patientID) {
        List<PastDiagnosesAndTreatments> diagnosesAndTreatments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(patientID)) { // Check if the patient ID matches
                    for (int i = 1; i < fields.length; i += 2) {
                        String diagnosis = fields[i].trim();
                        String treatment = fields[i + 1].trim();
                        diagnosesAndTreatments.add(new PastDiagnosesAndTreatments(diagnosis, treatment));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diagnosesAndTreatments;
    }

    // Save all diagnoses and treatments for a given patient ID
    public void addSingleRecord(String patientID, PastDiagnosesAndTreatments treatment) {
        // Construct the new line to append
        String newLine = patientID + "," + treatment.getDiagnosis() + "," + treatment.getTreatment();

        // Append the new record to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) { // Open in append mode
            bw.write(newLine);
            bw.newLine();
            System.out.println("Record added successfully for Patient ID: " + patientID);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    // Retrieve all diagnoses and treatments from the CSV file (for admin or debugging purposes)
    public List<PastDiagnosesAndTreatments> getAllDiagnosesAndTreatments() {
        List<PastDiagnosesAndTreatments> allDiagnosesAndTreatments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String patientID = fields[0].trim();
                for (int i = 1; i < fields.length; i += 2) {
                    String diagnosis = fields[i].trim();
                    String treatment = fields[i + 1].trim();
                    allDiagnosesAndTreatments.add(new PastDiagnosesAndTreatments(diagnosis, treatment, patientID));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDiagnosesAndTreatments;
    }
}

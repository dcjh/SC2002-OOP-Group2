package Data.DataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The PatientDAO class handles data access for patient records.
 * This includes operations such as loading all patients, saving new or updated records,
 * finding specific patients, and deleting patient records from the CSV file.
 */
public class PatientDAO {
    private static final String FILE_PATH = "src/Data/Assets/Patient_List.csv";

    /**
     * Loads all patient records from the CSV file.
     * 
     * @return A list of patients, where each patient is represented as a map of attributes.
     */
    public List<Map<String, String>> loadAll() {
        List<Map<String, String>> patientList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = br.readLine(); // Read and skip header line
            if (line == null) {
                System.out.println("The file is empty.");
                return patientList;
            }

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 8) {
                    System.err.println("Skipping malformed row: " + line);
                    continue;
                }

                Map<String, String> patient = new HashMap<>();
                patient.put("Patient ID", values[0].trim());
                patient.put("Name", values[1].trim());
                patient.put("Date of Birth", values[2].trim());
                patient.put("Gender", values[3].trim());
                patient.put("Blood Type", values[4].trim());
                patient.put("Allergies", values[5].trim());
                patient.put("Phone Number", values[6].trim());
                patient.put("Contact Information", values[7].trim());
                patientList.add(patient);
            }
        } catch (IOException e) {
            System.err.println("Error loading patients from file: " + FILE_PATH + ". Details: " + e.getMessage());
        }
        return patientList;
    }

    /**
     * Saves or updates a patient record to the CSV file.
     * If a patient with the given ID already exists, it will be updated; otherwise, it will be added.
     * 
     * @param patient A map representing the patient's attributes.
     */
    public void save(Map<String, String> patient) {
        List<Map<String, String>> patientList = loadAll();
        boolean exists = false;

        for (int i = 0; i < patientList.size(); i++) {
            if (patientList.get(i).get("Patient ID").equals(patient.get("Patient ID"))) {
                patientList.set(i, patient);
                exists = true;
                break;
            }
        }

        if (!exists) {
            patientList.add(patient);
        }

        saveAll(patientList);
    }

    /**
     * Finds a specific patient by their ID.
     * 
     * @param patientId The ID of the patient to find.
     * @param searchKey Optional search key (not used in the current implementation).
     * @return A map representing the patient's attributes, or null if not found.
     */
    public Map<String, String> find(String patientId, String searchKey) {
        List<Map<String, String>> patientList = loadAll();
        for (Map<String, String> patient : patientList) {
            if (patient.get("Patient ID").equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Deletes a patient record by their ID.
     * 
     * @param patientId The ID of the patient to delete.
     * @param searchKey Optional search key (not used in the current implementation).
     */
    public void delete(String patientId, String searchKey) {
        List<Map<String, String>> patientList = loadAll();
        patientList.removeIf(patient -> patient.get("Patient ID").equals(patientId));
        saveAll(patientList);
    }

    /**
     * Saves all patient records to the CSV file.
     * 
     * @param patientList A list of maps representing the patients to save.
     */
    private void saveAll(List<Map<String, String>> patientList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            writer.write("Patient ID,Name,Date of Birth,Gender,Blood Type,Allergies,Phone Number,Contact Information");
            writer.newLine();
            for (Map<String, String> patient : patientList) {
                writer.write(formatPatientCSVLine(patient));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving patient data: " + e.getMessage());
        }
    }

    /**
     * Formats a patient record as a CSV line.
     * 
     * @param patient A map representing the patient's attributes.
     * @return A CSV-formatted string.
     */
    private String formatPatientCSVLine(Map<String, String> patient) {
        return String.join(",",
                patient.get("Patient ID"),
                patient.get("Name"),
                patient.get("Date of Birth"),
                patient.get("Gender"),
                patient.get("Blood Type"),
                patient.get("Allergies"),
                patient.get("Phone Number"),
                patient.get("Contact Information")
        );
    }
}

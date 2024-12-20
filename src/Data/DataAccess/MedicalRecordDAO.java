package Data.DataAccess;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Model.Shared.MedicalRecord;

/**
 * The MedicalRecordDAO class handles the data access operations for medical records.
 * This includes loading, adding, and updating medical records from/to a CSV file.
 */
public class MedicalRecordDAO {

    private static final String FILE_PATH = "src/Data/Assets/MedicalRecord.csv";

    /**
     * Loads a single medical record by patient ID from the CSV file.
     *
     * @param patientID The ID of the patient whose medical record is to be loaded.
     * @return The MedicalRecord object if found, otherwise null.
     */
    public MedicalRecord loadSingleRecord(String patientID) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) { // Skip the header row
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                if (values[0].equals(patientID)) { // Find the matching patient ID
                    String name = values.length > 1 ? values[1] : "";
                    String dob = values.length > 2 ? values[2] : "";
                    String gender = values.length > 3 ? values[3] : "";
                    String bloodType = values.length > 4 ? values[4] : "";
                    String allergies = values.length > 5 ? values[5] : "";
                    String phoneNumber = values.length > 6 ? values[6] : "";
                    String email = values.length > 7 ? values[7] : "";

                    return new MedicalRecord(patientID, name, dob, gender, phoneNumber, email, bloodType, allergies);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading medical record: " + e.getMessage());
        }
        return null; // Return null if the record is not found
    }

    /**
     * Adds a new medical record to the CSV file.
     *
     * @param newRecord The MedicalRecord object to add.
     */
    public void addMedicalRecord(MedicalRecord newRecord) {
        String newRecordLine = formatMedicalRecordCSVLine(newRecord); // Format the new record

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) { // Open in append mode
            File file = new File(FILE_PATH);
            if (file.length() > 0) {
                writer.newLine(); // Add a newline only if the file already has content
            }
            writer.write(newRecordLine); // Write the new record to the file
        } catch (IOException e) {
            System.err.println("Error adding new medical record: " + e.getMessage());
        }
    }

    /**
     * Updates a single medical record by rewriting the CSV file with the modified record.
     *
     * @param updatedRecord The updated MedicalRecord object.
     */
    public void updateSingleRecord(MedicalRecord updatedRecord) {
        List<String> lines = new ArrayList<>();
        String updatedLine = formatMedicalRecordCSVLine(updatedRecord); // Format the updated record as a single line

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    lines.add(line); // Add the header row as-is
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                // Check if this line is the record to be updated
                if (values[0].equals(updatedRecord.getPatientID())) {
                    lines.add(updatedLine); // Replace with the updated line
                } else {
                    lines.add(line); // Keep other records unchanged
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading medical records: " + e.getMessage());
        }

        // Rewrite the entire file with updated content
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) { // Overwrite mode
            for (String outputLine : lines) {
                writer.write(outputLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving updated medical record: " + e.getMessage());
        }
    }

    /**
     * Formats a MedicalRecord object as a CSV line.
     *
     * @param medicalRecord The MedicalRecord object to format.
     * @return A CSV-formatted line representing the medical record.
     */
    private String formatMedicalRecordCSVLine(MedicalRecord medicalRecord) {
        return String.join(",",
                medicalRecord.getPatientID(),
                medicalRecord.getName(),
                medicalRecord.getDob(),
                medicalRecord.getGender(),
                medicalRecord.getBloodType(),
                medicalRecord.getAllergies(),
                medicalRecord.getPhoneNumber(),
                medicalRecord.getEmail()
        );
    }
}

package Data.DataAccess;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO implements DataAccessObject<MedicalRecord, String> {
    
    private static final String FILE_PATH = "src/DataFiles/MedicalRecords.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // Load all medical records for a specific patient ID with inline AppointmentOutcomeRecords
    @Override
    public List<MedicalRecord> loadAll(String patientID) {
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(patientID)) {  // Filter by patient ID
                    String dob = values[1];
                    String gender = values[2];
                    String phoneNumber = values[3];
                    String email = values[4];
                    String bloodType = values[5];
                    String allergies = values[6];
                    
                    // Parse appointment outcomes inline, separated by ";"
                    List<AppointmentOutcomeRecord> outcomes = parseAppointmentOutcomes(values[7]);

                    MedicalRecord medicalRecord = new MedicalRecord(patientID, dob, gender, phoneNumber,
                            email, bloodType, allergies, outcomes);
                    medicalRecordList.add(medicalRecord);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading medical records: " + e.getMessage());
        }
        return medicalRecordList;
    }

    // Load a single MedicalRecord by patientID
    public MedicalRecord loadSingleRecord(String patientID) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(patientID)) {  // Find the matching patient ID
                    String dob = values[1];
                    String gender = values[2];
                    String phoneNumber = values[3];
                    String email = values[4];
                    String bloodType = values[5];
                    String allergies = values[6];
                    
                    // Parse appointment outcomes from the inline format
                    List<AppointmentOutcomeRecord> outcomes = parseAppointmentOutcomes(values[7]);

                    return new MedicalRecord(patientID, dob, gender, phoneNumber, email, bloodType, allergies, outcomes);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading medical record: " + e.getMessage());
        }
        return null; // Return null if the record is not found
    }

    // Save all medical records to the CSV file
    @Override
    public void saveAll(List<MedicalRecord> medicalRecordList, String patientID) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) { // overwrite mode
            for (MedicalRecord medicalRecord : medicalRecordList) {
                writer.write(formatMedicalRecordCSVLine(medicalRecord));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving medical records: " + e.getMessage());
        }
    }

    // Update a single MedicalRecord by rewriting the CSV with the modified record
    public void updateSingleRecord(MedicalRecord updatedRecord) {
        List<String> lines = new ArrayList<>();
        String updatedLine = formatMedicalRecordCSVLine(updatedRecord); // Format the updated record as a single line

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
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

    // Helper method to format a MedicalRecord object with inline AppointmentOutcomeRecords
    private String formatMedicalRecordCSVLine(MedicalRecord medicalRecord) {
        return String.join(",",
                medicalRecord.getPatientID(),
                medicalRecord.getDob(),
                medicalRecord.getGender(),
                medicalRecord.getPhoneNumber(),
                medicalRecord.getEmail(),
                medicalRecord.getBloodType(),
                medicalRecord.getAllergies(),
                formatAppointmentOutcomes(medicalRecord.getAppointmentOutcomes()) // Inline outcomes
        );
    }

    // Parse appointment outcomes from a single CSV field with ";" as separator
    private List<AppointmentOutcomeRecord> parseAppointmentOutcomes(String outcomesField) {
        List<AppointmentOutcomeRecord> outcomes = new ArrayList<>();
        String[] outcomeStrings = outcomesField.split(";");
        for (String outcomeString : outcomeStrings) {
            String[] fields = outcomeString.split(",");
            String date = fields[0];
            String diagnoses = fields[1];
            String treatments = fields[2];
            String prescribedMedications = fields[3];
            boolean status = Boolean.parseBoolean(fields[4]);
            String consultationNotes = fields[5];
            LocalDateTime updateTimestamps = LocalDateTime.parse(fields[6], DATE_FORMATTER);

            AppointmentOutcomeRecord outcome = new AppointmentOutcomeRecord(diagnoses, treatments,
                    prescribedMedications, status, consultationNotes, updateTimestamps);
            outcomes.add(outcome);
        }
        return outcomes;
    }

    // Format a list of AppointmentOutcomeRecords as a single CSV field
    private String formatAppointmentOutcomes(List<AppointmentOutcomeRecord> outcomes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < outcomes.size(); i++) {
            AppointmentOutcomeRecord outcome = outcomes.get(i);
            if (i > 0) builder.append(";"); // Separate multiple outcomes with ";"
            builder.append(outcome.getDate()).append(",")
                   .append(outcome.getDiagnoses()).append(",")
                   .append(outcome.getTreatments()).append(",")
                   .append(outcome.getPrescribedMedications()).append(",")
                   .append(outcome.isStatus()).append(",")
                   .append(outcome.getConsultationNotes()).append(",")
                   .append(outcome.getUpdateTimestamps().format(DATE_FORMATTER));
        }
        return builder.toString();
    }
}

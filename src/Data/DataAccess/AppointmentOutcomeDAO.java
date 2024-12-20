package Data.DataAccess;

import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The AppointmentOutcomeDAO class handles data access for appointment outcomes.
 * This includes loading, saving, finding, and serializing appointment outcomes.
 */
public class AppointmentOutcomeDAO {
    private final String filePath = "src/Data/Assets/AppointmentOutcome.csv";

    /**
     * Saves an appointment outcome to the CSV file, either by appending or updating an existing record.
     *
     * @param outcome the appointment outcome to save
     */
    public void save(AppointmentOutcome outcome) {
        // First, load all appointment outcomes
        List<AppointmentOutcome> outcomes = loadAll();

        // Check if the appointment outcome already exists
        boolean exists = false;
        for (int i = 0; i < outcomes.size(); i++) {
            if (outcomes.get(i).getAppointmentOutcomeID().equals(outcome.getAppointmentOutcomeID())) {
                outcomes.set(i, outcome); // Update existing appointment outcome
                exists = true;
                break;
            }
        }

        // If the appointment outcome doesn't exist, add it to the list
        if (!exists) {
            outcomes.add(outcome);
        }

        // Write the updated list back to the CSV file
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("AppointmentOutcomeID,DoctorID,PatientID,AppointmentID,Date,Time,TypeOfService,PrescribedMedications,ConsultationNotes"); // CSV header
            for (AppointmentOutcome o : outcomes) {
                pw.printf("%s,%s,%s,%s,%s,%s,%s,\"%s\",%s%n",
                        o.getAppointmentOutcomeID(),
                        o.getDoctorID(),
                        o.getPatientID(),
                        o.getAppointmentID(),
                        o.getDate(),
                        o.getTime(),
                        o.getTypeOfService(),
                        serializeMedications(o.getPrescribedMedications()),
                        o.getConsultationNotes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all appointment outcomes from the CSV file.
     *
     * @return a list of all appointment outcomes
     */
    public List<AppointmentOutcome> loadAll() {
        List<AppointmentOutcome> outcomes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", 9);
                if (fields.length == 9) {
                    AppointmentOutcome outcome = new AppointmentOutcome(
                            fields[4],
                            fields[5],
                            fields[6],
                            deserializeMedications(fields[7]),
                            fields[8],
                            fields[1],
                            fields[2],
                            fields[3],
                            fields[0]
                    );
                    outcomes.add(outcome);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outcomes;
    }

    /**
     * Helper method to serialize prescribed medications to a string format for saving in CSV.
     *
     * @param medications the list of prescribed medications to serialize
     * @return the serialized string representation of medications
     */
    private String serializeMedications(List<PrescribedMedication> medications) {
        List<String> serialized = new ArrayList<>();
        for (PrescribedMedication med : medications) {
            serialized.add(String.format("%s:%d:%s", med.getMedicineName(), med.getQuantity(), med.getStatus()));
        }
        return "[" + String.join("-", serialized) + "]";
    }

    /**
     * Helper method to deserialize prescribed medications from a string format loaded from CSV.
     *
     * @param data the serialized string representation of medications
     * @return a list of prescribed medications
     */
    private ArrayList<PrescribedMedication> deserializeMedications(String data) {
        ArrayList<PrescribedMedication> medications = new ArrayList<>();
        data = data.substring(2, data.length() - 2); // Remove brackets
        String[] meds = data.split("-");
        for (String med : meds) {
            String[] parts = med.split(":");
            if (parts.length == 3) {
                PrescribedMedication medication = new PrescribedMedication(parts[0], Integer.parseInt(parts[1]));
                if (parts[2].equals("dispensed")){
                    medication.setStatusDispensed();
                }
                medications.add(medication);
            }
        }
        return medications;
    }

    /**
     * Finds a specific appointment outcome by its ID.
     *
     * @param appointmentOutcomeId the ID of the appointment outcome to find
     * @return the appointment outcome if found, otherwise null
     */
    public AppointmentOutcome find(String appointmentOutcomeId) {
        List<AppointmentOutcome> outcomes = loadAll();
        for (AppointmentOutcome outcome : outcomes) {
            if (outcome.getAppointmentOutcomeID().equals(appointmentOutcomeId)) {
                return outcome;
            }
        }
        return null; // Return null if not found
    }

    /**
     * Finds all appointment outcomes for a specific doctor by doctor ID.
     *
     * @param doctorId the ID of the doctor
     * @return a list of appointment outcomes for the specified doctor
     */
    public List<AppointmentOutcome> getAppointmentOutcomeByDoctorID(String doctorId) {
        List<AppointmentOutcome> outcomes = loadAll();
        List<AppointmentOutcome> result = new ArrayList<>();
        for (AppointmentOutcome outcome : outcomes) {
            if (outcome.getDoctorID().equals(doctorId)) {
                result.add(outcome);
            }
        }
        return result;
    }

    /**
     * Finds all appointment outcomes for a specific patient by patient ID.
     *
     * @param patientId the ID of the patient
     * @return a list of appointment outcomes for the specified patient
     */
    public List<AppointmentOutcome> getAppointmentOutcomeByPatientID(String patientId) {
        List<AppointmentOutcome> outcomes = loadAll();
        List<AppointmentOutcome> result = new ArrayList<>();
        for (AppointmentOutcome outcome : outcomes) {
            if (outcome.getPatientID().equals(patientId)) {
                result.add(outcome);
            }
        }
        return result;
    }
}

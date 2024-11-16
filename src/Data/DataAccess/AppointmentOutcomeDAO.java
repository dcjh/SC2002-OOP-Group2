package Data.DataAccess;

import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentOutcomeDAO {
    private final String filePath = "src/Data/Assets/AppointmentOutcome.csv";

    // Save an appointment outcome to the CSV file (append or update)
    public void save(AppointmentOutcome outcome) {
        // First, load all appointments
        List<AppointmentOutcome> outcomes = loadAll();
        
        // Check if the appointment already exists
        boolean exists = false;
        for (int i = 0; i < outcomes.size(); i++) {
            if (outcomes.get(i).getAppointmentOutcomeID().equals(outcome.getAppointmentOutcomeID())) {
                outcomes.set(i, outcome); // Update existing appointment
                exists = true;
                break;
            }
        }
        
        // If the appointment doesn't exist, add it to the list
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

    // Retrieve all AppointmentOutcomes
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

    // Helper: Serialize medications to a string
    private String serializeMedications(List<PrescribedMedication> medications) {
        List<String> serialized = new ArrayList<>();
        for (PrescribedMedication med : medications) {
            serialized.add(String.format("%s:%d:%s", med.getMedicineName(), med.getQuantity(), med.getStatus()));
        }
        return "[" + String.join("-", serialized) + "]";
    }

    // Helper: Deserialize medications from a string
    private ArrayList<PrescribedMedication> deserializeMedications(String data) {
        ArrayList<PrescribedMedication> medications = new ArrayList<>();
        data = data.substring(1, data.length() - 2); // Remove brackets
        String[] meds = data.split("-");
        for (String med : meds) {
            String[] parts = med.split(":");
            if (parts.length == 3) {
                PrescribedMedication medication = new PrescribedMedication(parts[0], Integer.parseInt(parts[1]));
                medications.add(medication);
            }
        }
        return medications;
    }

    // Find a specific appointment by Appointment Outcome ID and a search key
    public AppointmentOutcome find(String appointmentOutcomeId) {
        List<AppointmentOutcome> outcomes = loadAll();
        for (AppointmentOutcome outcome : outcomes) {
            if (outcome.getAppointmentOutcomeID().equals(appointmentOutcomeId)) {
                return outcome;
            }
        }
        return null; // Return null if not found
    }

    // Find appointment outcome by doctorId
    public List<AppointmentOutcome> getAppointmentOutcomeByDoctorID(String doctorId) {
        List<AppointmentOutcome> outcomes = loadAll();
        List<AppointmentOutcome> result = new ArrayList<>();
        for (AppointmentOutcome outcome : outcomes) {
            if (outcome.getDoctorID().equals(doctorId)) {
                result.add(outcome);
            }
        }
        return result; // Return the filtered list
    }

    // Find appointment outcome by patientId
    public List<AppointmentOutcome> getAppointmentOutcomeByPatientID(String patientId) {
        List<AppointmentOutcome> outcomes = loadAll();
        List<AppointmentOutcome> result = new ArrayList<>();
        for (AppointmentOutcome outcome : outcomes) {
            if (outcome.getPatientID().equals(patientId)) {
                result.add(outcome);
            }
        }
        return result; // Return the filtered list
    }

}

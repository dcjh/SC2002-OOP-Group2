package Data.DataAccess;

import Model.Shared.Appointment;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The AppointmentDAO class is responsible for managing the data access operations for appointments.
 * This includes loading, saving, finding, and deleting appointment records from the CSV file.
 */
public class AppointmentDAO {
    private final String filePath = "src/Data/Assets/Appointment.csv";

    /**
     * Loads all appointments from the CSV file.
     *
     * @return a list of all appointments
     */
    public List<Appointment> loadAll() {
        List<Appointment> appointments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line if present
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    Appointment appointment = new Appointment(
                            values[1].trim(),
                            values[2].trim(),
                            values[5].trim(),
                            values[4].trim(),
                            values[0].trim(),
                            values[3].trim()
                    );
                    appointments.add(appointment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    /**
     * Saves an appointment to the CSV file, either by appending or updating an existing record.
     *
     * @param appointment the appointment to save
     */
    public void save(Appointment appointment) {
        // First, load all appointments
        List<Appointment> appointments = loadAll();

        // Check if the appointment already exists
        boolean exists = false;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentID().equals(appointment.getAppointmentID())) {
                appointments.set(i, appointment); // Update existing appointment
                exists = true;
                break;
            }
        }

        // If the appointment doesn't exist, add it to the list
        if (!exists) {
            appointments.add(appointment);
        }

        // Write the updated list back to the CSV file
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("Appointment ID,Doctor ID,Patient ID,Status,Date,Time"); // CSV header
            for (Appointment a : appointments) {
                pw.printf("%s,%s,%s,%s,%s,%s%n",
                        a.getAppointmentID(),
                        a.getDocID(),
                        a.getPatientID(),
                        a.getStatus(),
                        a.getDate(),
                        a.getTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds a specific appointment by ID and optionally a search key (status).
     *
     * @param appointmentId the ID of the appointment to find
     * @param searchKey     the status of the appointment to filter by (optional)
     * @return the appointment if found, otherwise null
     */
    public Appointment find(String appointmentId, String searchKey) {
        List<Appointment> appointments = loadAll();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentId) &&
                    (searchKey == null || appointment.getStatus().equals(searchKey))) {
                return appointment;
            }
        }
        return null; // Return null if not found
    }

    /**
     * Finds all appointments for a specific doctor by doctor ID.
     *
     * @param doctorId the ID of the doctor
     * @return a list of appointments for the specified doctor
     */
    public List<Appointment> getAppointmentsByDocID(String doctorId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDocID().equals(doctorId)) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Finds all approved appointments for a specific doctor by doctor ID.
     *
     * @param doctorId the ID of the doctor
     * @return a list of approved appointments for the specified doctor
     */
    public List<Appointment> getApprovedAppointmentsByDocID(String doctorId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDocID().equals(doctorId) && appointment.getStatus().equals("approved")) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Finds all pending appointments for a specific doctor by doctor ID.
     *
     * @param doctorId the ID of the doctor
     * @return a list of pending appointments for the specified doctor
     */
    public List<Appointment> getPendingAppointmentsByDocID(String doctorId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDocID().equals(doctorId) && appointment.getStatus().equals("pending")) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Finds all pending appointments for a specific doctor on a specific date.
     *
     * @param doctorId the ID of the doctor
     * @param date     the date of the appointment
     * @return a list of pending appointments for the specified doctor and date
     */
    public List<Appointment> getPendingAppointmentsByDocIDandDate(String doctorId, String date) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDocID().equals(doctorId) && appointment.getStatus().equals("pending") &&
                    appointment.getDate().equals(date)) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Finds all appointments for a specific patient by patient ID.
     *
     * @param patientId the ID of the patient
     * @return a list of appointments for the specified patient
     */
    public List<Appointment> getAppointmentsByPatientID(String patientId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientID().equals(patientId)) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Finds all approved appointments for a specific patient by patient ID.
     *
     * @param patientId the ID of the patient
     * @return a list of approved appointments for the specified patient
     */
    public List<Appointment> getApprovedAppointmentsByPatientID(String patientId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientID().equals(patientId) && appointment.getStatus().equals("approved")) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Deletes an appointment by ID and optionally a search key (status).
     *
     * @param appointmentId the ID of the appointment to delete
     * @param searchKey     the status of the appointment to filter by (optional)
     */
    public void delete(String appointmentId, String searchKey) {
        List<Appointment> appointments = loadAll();
        appointments.removeIf(appointment ->
                appointment.getAppointmentID().equals(appointmentId) &&
                        (searchKey == null || appointment.getStatus().equals(searchKey)));

        // Write the updated list back to the CSV file
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("Appointment ID,Doctor ID,Patient ID,Status,Date,Time"); // CSV header
            for (Appointment a : appointments) {
                pw.printf("%s,%s,%s,%s,%s,%s%n",
                        a.getAppointmentID(),
                        a.getDocID(),
                        a.getPatientID(),
                        a.getStatus(),
                        a.getDate(),
                        a.getTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

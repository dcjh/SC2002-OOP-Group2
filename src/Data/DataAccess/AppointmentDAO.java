package Data.DataAccess;

import Model.Shared.Appointment;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO implements DataAccessObject<Appointment, String> {
    private final String filePath;

    // Constructor to set file path
    public AppointmentDAO(String filePath) {
        this.filePath = filePath;
    }

    // Load all appointments from the CSV file
    @Override
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
                            values[3].trim(),
                            values[4].trim()
                    );
                    appointments.add(appointment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Save an appointment to the CSV file (append or update)
    @Override
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

    // Find a specific appointment by ID and a search key
    @Override
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

    // Delete an appointment by ID and a search key
    @Override
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


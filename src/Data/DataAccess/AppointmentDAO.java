package Data.DataAccess;

import Model.Shared.Appointment;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private final String filePath = "src/Data/Assets/Appointment.csv";

    
    /** 
     * @return List<Appointment>
     */
    // Load all appointments from the CSV file
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
     * @param appointment
     */
    // Save an appointment to the CSV file (append or update)
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

    // Find appointments by doctorId
    public List<Appointment> getAppointmentsByDocID(String doctorId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDocID().equals(doctorId)) {
                result.add(appointment);
            }
        }
        return result; // Return the filtered list
    }

    // Find approved appointments by doctorId
    public List<Appointment> getApprovedAppointmentsByDocID(String doctorId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDocID().equals(doctorId) && appointment.getStatus().equals("approved")) {
                result.add(appointment);
            }
        }
        return result; // Return the filtered list
    }

    // // Find patientId by appoinmentId
    // public String getPatientID(String appointmentId) {
    //     List<Appointment> appointments = loadAll();
    //     for (Appointment appointment : appointments) {
    //         if (appointment.getAppointmentID().equals(appointmentId)) {
    //             return appointment.getPatientID();
    //         }
    //     }
    //     return null; // Return null if not found
    // }

    // // Find doctorId by appoinmentId
    // public String getDoctorID(String appointmentId) {
    //     List<Appointment> appointments = loadAll();
    //     for (Appointment appointment : appointments) {
    //         if (appointment.getAppointmentID().equals(appointmentId)) {
    //             return appointment.getDocID();
    //         }
    //     }
    //     return null; // Return null if not found
    // }

    // Find pending appointments by doctorId as doctor need to quickly filter these to see which they can accept
    public List<Appointment> getPendingAppointmentsByDocID(String doctorId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDocID().equals(doctorId) && appointment.getStatus().equals("pending")) {
                result.add(appointment);
            }
        }
        return result; // Return the filtered list
    }

    public List<Appointment> getPendingAppointmentsByDocIDandDate(String doctorId, String date) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDocID().equals(doctorId) && appointment.getStatus().equals("pending") &&appointment.getDate().equals(date)) {
                result.add(appointment);
            }
        }
        return result; // Return the filtered list
    }

    // Find appointments by patientId
    public List<Appointment> getAppointmentsByPatientID(String patientId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientID().equals(patientId)) {
                result.add(appointment);
            }
        }
        return result; // Return the filtered list
    }

    // Find approved appointments by patientId
    public List<Appointment> getApprovedAppointmentsByPatientID(String patientId) {
        List<Appointment> appointments = loadAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientID().equals(patientId) && appointment.getStatus().equals("approved")) {
                result.add(appointment);
            }
        }
        return result; // Return the filtered list
    }


    // Delete an appointment by ID and a search key
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


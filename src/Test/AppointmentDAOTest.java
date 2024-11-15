package Test;

import Data.DataAccess.AppointmentDAO;
import Model.Shared.Appointment;
import java.util.List;

public class AppointmentDAOTest {
    public static void main(String[] args) {
        String filePath = "src/Data/Assets/Appointment.csv"; 
        AppointmentDAO appointmentDAO = new AppointmentDAO(filePath);

        // 1. Load all appointments and print them
        System.out.println("All Appointments:");
        List<Appointment> appointments = appointmentDAO.loadAll();
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
        
        // 2. Add new appointments
        System.out.println("\nAdding a new appointment...");
        Appointment newAppointment = new Appointment("D001", "P1001", "10:30", "25-11-2024");
        appointmentDAO.save(newAppointment);

        Appointment newAppointment1 = new Appointment("D002", "P1002", "11:30", "05-11-2024");
        appointmentDAO.save(newAppointment1);

        Appointment newAppointment2 = new Appointment("D003", "P1003", "11:35", "06-11-2024");
        appointmentDAO.save(newAppointment2);

        Appointment newAppointment3 = new Appointment("D004", "P1004", "11:40", "07-11-2024");
        appointmentDAO.save(newAppointment3);

        // 3. Find an appointment by Appointment ID
        System.out.println("\nFinding an appointment with ID AP0002...");
        Appointment foundAppointment = appointmentDAO.find("AP0002", null); // Search without a specific status
        if (foundAppointment != null) {
            System.out.println("Appointment found:\n" + foundAppointment);
        } else {
            System.out.println("Appointment with ID AP0001 not found.");
        }

        // 4. Update an appointment's status
        System.out.println("\nUpdating appointment AP0001 to status 'completed'...");
        Appointment updatedAppointment = newAppointment;
        updatedAppointment.setStatus("completed");
        appointmentDAO.save(updatedAppointment); // Save will update if the ID exists

        // 5. Delete an appointment by ID
        System.out.println("\nDeleting appointment with ID AP0003...");
        appointmentDAO.delete("AP0003", null); // Delete without specifying a status
    }
}

package Repository;

import Data.DataAccess.AppointmentDAO;
import Data.DataAccess.AvailabilityDAO;
import Data.DataAccess.MedicalRecordDAO;
import Model.Shared.Availability;
import Model.Shared.Appointment;
import Model.Shared.MedicalRecord;

import java.util.List;

public class DoctorRepository {
    private AvailabilityDAO availabilityDAO;
    private AppointmentDAO appointmentDAO;
    private MedicalRecordDAO medicalRecordDAO;

    public DoctorRepository(AvailabilityDAO availabilityDAO, AppointmentDAO appointmentDAO, MedicalRecordDAO medicalRecordDAO) {
        this.availabilityDAO = availabilityDAO;
        this.appointmentDAO = appointmentDAO;
        this.medicalRecordDAO = medicalRecordDAO;
    }

    public List<Availability> getAvailabilityByDoctorId(String doctorId) {
        return availabilityDAO.getAvailabilityByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByDoctorId(String doctorId) {
        return appointmentDAO.getAppointmentsByDoctorId(doctorId);
    }

    public List<MedicalRecord> getMedicalRecordsForPatient(String doctorId, String patientId) {
        return medicalRecordDAO.getMedicalRecordsByDoctorAndPatient(doctorId, patientId);
    }

    public boolean updateDoctorAvailability(String doctorId, List<Availability> availabilities) {
        return availabilityDAO.updateAvailability(doctorId, availabilities);
    }

    public Appointment getAppointmentById(String appointmentId) {
        return appointmentDAO.getAppointmentById(appointmentId);
    }

    public boolean updateAppointment(Appointment appointment) {
        return appointmentDAO.updateAppointment(appointment);
    }
}

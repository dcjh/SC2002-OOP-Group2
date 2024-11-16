package Controller;


import java.time.LocalDate;
import java.util.List;
/*import Controller.AppointmentController;
import Controller.MedicalRecordController;*/
import Model.Roles.Patient;

public class PatientController {
    private Patient patient;
    private MedicalRecordController medicalRecordController;
    private AppointmentController appointmentController;

    public PatientController(Patient patient) {
        this.patient = patient;
        this.medicalRecordController = new MedicalRecordController();
        this.appointmentController = new AppointmentController();
    }

    public void updateContactInformation(String newPhoneNumber, String newEmail) {
        medicalRecordController.updateContactInformation(patient.getHosID(), newPhoneNumber, newEmail);
    }
    public void scheduleAppointment(String docID, String date, String time) {
        appointmentController.createAppointment(docID, patient.getHosID(), time, date);
    }
    public void rescheduleAppointment(String appointmentID, String newDate, String newTime) {
        appointmentController.updateAppointmentReschedule(appointmentID, newDate, newTime);
    }
    public void cancelAppointment(String appointmentID) {
        appointmentController.updateAppointmentStatus(appointmentID, "cancelled");
    }
    public void viewScheduledAppointments(List<Appointment> appointments) {
        appointmentController.viewAppointmentsByPatientID(patient.getHosID());
    }
    public void viewMedicalRecord() {
        medicalRecordController.viewMedicalRecord(patient.getHosID());
    }
    public void viewPastAppointments() {
        medicalRecordController.viewPastAppointments(patient.getHosID());//need to chnage once the dao have been added for apptoutcomerecord
    }
}

package Controller;


import java.time.LocalDate;
import java.util.List;
/*import Controller.AppointmentController;
import Controller.MedicalRecordController;*/
import Model.Roles.Patient;
import Model.Shared.Appointment;

public class PatientController {
    private Patient patient;
    private MedicalRecordController medicalRecordController;
    private AppointmentController appointmentController;
    private ScheduleController scheduleController;


    public PatientController(Patient patient) {
        this.patient = patient;
        this.medicalRecordController = new MedicalRecordController();
        this.appointmentController = new AppointmentController();
        this.scheduleController = new ScheduleController(null, this);
    }

    // public void updateContactInformation(String newPhoneNumber, String newEmail) {
    //     medicalRecordController.updateContactInformation(patient.getHosID(), newPhoneNumber, newEmail);
    // }
    public void scheduleAppointment(String docID, String date, String time) {
        scheduleController.patientScheduleView();
    }
    // public void rescheduleAppointment(String appointmentID, String newDate, String newTime) {
    //     appointmentController.updateAppointmentReschedule(appointmentID, newDate, newTime);
    // }
    // public void cancelAppointment(String appointmentID) {
    //     appointmentController.updateAppointmentStatus(appointmentID, "cancelled");
    // }
    // public void viewScheduledAppointments() {
    //     appointmentController.viewAppointmentsByPatientID(patient.getHosID());
    // }
    // public void viewMedicalRecord() {
    //     medicalRecordController.viewMedicalRecord(patient.getHosID());
    // }
    // public void viewPastAppointments() {
    //     medicalRecordController.viewPastAppointments(patient.getHosID());//need to chnage once the dao have been added for apptoutcomerecord
    // }

    // //navigate to viewing all doctor schedules
    // public void createAppointment(String docID, String date, String time) {
    //     appointmentController.createAppointment(docID, patient.getHosID(), time, date);
    // }

}

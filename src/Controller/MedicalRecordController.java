package Controller;

import Model.Shared.MedicalRecord;
import Model.Shared.AppointmentOutcomeRecord;
import Data.DataAccess.MedicalRecordDAO;
import View.MedicalRecordView;
import View.Appointments.PastAppointmentView;
import View.Doctor.DoctorMedicalRecordView;

import java.util.List;

public class MedicalRecordController {
    private MedicalRecordDAO medicalRecordDAO;
    private MedicalRecordView medicalRecordView;
    private PastAppointmentView pastAppointmentView;
    private DoctorMedicalRecordView doctorMedicalRecordView;
    private DoctorController doctorController;

    public MedicalRecordController(DoctorController doctorController) {
        this.medicalRecordDAO = new MedicalRecordDAO();
        this.medicalRecordView = new MedicalRecordView();
        this.pastAppointmentView = new PastAppointmentView();
        this.doctorMedicalRecordView = new DoctorMedicalRecordView(this);
        this.doctorController = doctorController;
    }

    //navigate to medical record View
    public void doctorMedicalRecordView(String doctorId) {
        doctorMedicalRecordView.menu(doctorId);
    }

    //navigate to update medical record view
    public void updateMedicalRecordView(String patientId) {

    }

    public void viewMedicalRecord(String patientID) {
        MedicalRecord medicalRecord = medicalRecordDAO.loadSingleRecord(patientID);
        if (medicalRecord != null) {
            medicalRecordView.fullDisplay(medicalRecord);
        } else {
            System.out.println("Medical record not found for patient ID: " + patientID);
        }
    }

    public void updateContactInformation(String patientID, String newPhoneNumber, String newEmail) {
        MedicalRecord medicalRecord = medicalRecordDAO.loadSingleRecord(patientID);
        if (medicalRecord != null) {
            medicalRecord.setPhoneNumber(newPhoneNumber);
            medicalRecord.setEmail(newEmail);
            medicalRecordDAO.updateSingleRecord(medicalRecord);
            System.out.println("Contact information updated successfully!");
        } else {
            System.out.println("Medical record not found for patient ID: " + patientID);
        }
    }

    public void createMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordDAO.saveNewRecord(medicalRecord);
        System.out.println("Medical record created successfully!");
    }


    public void viewPastAppointments(String patientID) {//need to chnage once the dao have been added for apptoutcomerecord
        MedicalRecord medicalRecord = medicalRecordDAO.loadSingleRecord(patientID);
        if (medicalRecord != null) {
            List<AppointmentOutcomeRecord> appointmentOutcomes = medicalRecord.getAppointmentOutcome();
            pastAppointmentView.displayPastAppointments(appointmentOutcomes);
        } else {
            System.out.println("No past appointments found for patient ID: " + patientID);
        }
    }

    public List<MedicalRecord> getMedicalRecordsUnderDoctor(String doctorId) {
        doctorController.getAppointmentreById(doctorId)
    }
}
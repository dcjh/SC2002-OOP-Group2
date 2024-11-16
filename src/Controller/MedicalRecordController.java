package View;

import Model.Shared.MedicalRecord;
import Data.DataAccess.MedicalRecordDAO;
import View.MedicalRecordView;
import Controller.AppointmentOutcomeController;
import java.util.List;
import View.Doctor.DoctorMedicalRecordView;

public class MedicalRecordController {
    private MedicalRecordDAO medicalRecordDAO;
    private MedicalRecordView medicalRecordView;
    private AppointmentOutcomeController appointmentOutcomeController;
    private DoctorController doctorController;


    public MedicalRecordController() {
        this.medicalRecordDAO = new MedicalRecordDAO();
        this.medicalRecordView = new MedicalRecordView();
        this.appointmentOutcomeController = new AppointmentOutcomeController();
        this.doctorController = doctorController;
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
        List<AppointmentOutcomeRecord> outcomes = appointmentOutcomeController.getAppointmentOutcomeByPatientID(patientID);
        if (outcomes != null && !outcomes.isEmpty()) {
            pastAppointmentView.displayPastAppointments(outcomes);
        } else {
            System.out.println("No past appointments found for patient ID: " + patientID);
        }
    }
}

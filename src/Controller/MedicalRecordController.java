package Controller;

import Model.Shared.MedicalRecord;
import Model.Shared.PrescribedMedication;
import Model.Shared.AppointmentOutcome;
import Data.DataAccess.MedicalRecordDAO;
import View.MedicalRecordView;
import View.Appointments.PastAppointmentView;
import View.Doctor.DoctorMedicalRecordView;
import View.Doctor.DoctorUpdateMedicalRecordView;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class MedicalRecordController {
    private MedicalRecordDAO medicalRecordDAO;
    private MedicalRecordView medicalRecordView;
    private PastAppointmentView pastAppointmentView;
    private DoctorMedicalRecordView doctorMedicalRecordView;
    private DoctorUpdateMedicalRecordView doctorUpdateMedicalRecordView;
    private DoctorController doctorController;
    private AppointmentOutcomeController appointmentOutcomeController;
    private PastDiagnosesAndTreatmentsController pastDiagnosesAndTreatmentsController;


    public MedicalRecordController(DoctorController doctorController) {
        this.medicalRecordDAO = new MedicalRecordDAO();
        this.medicalRecordView = new MedicalRecordView();
        this.pastAppointmentView = new PastAppointmentView();
        this.doctorMedicalRecordView = new DoctorMedicalRecordView(this);
        this.doctorUpdateMedicalRecordView = new DoctorUpdateMedicalRecordView(this);
        this.doctorController = doctorController;
        this.appointmentOutcomeController = new AppointmentOutcomeController();
        this.pastDiagnosesAndTreatmentsController = new PastDiagnosesAndTreatmentsController();
    }
    public MedicalRecordController() {
        this.medicalRecordDAO = new MedicalRecordDAO();
        this.medicalRecordView = new MedicalRecordView();
        this.pastAppointmentView = new PastAppointmentView();
        this.doctorMedicalRecordView = new DoctorMedicalRecordView(this);
        this.doctorUpdateMedicalRecordView = new DoctorUpdateMedicalRecordView(this);
        this.doctorController = null;
        this.appointmentOutcomeController = new AppointmentOutcomeController();
        this.pastDiagnosesAndTreatmentsController = new PastDiagnosesAndTreatmentsController();
    }

    //navigate to medical record View
    public void doctorMedicalRecordView(String doctorId) {
        doctorMedicalRecordView.menu(doctorId);
    }

    //navigate to update medical record view
    public void updateMedicalRecordView(String doctorId) {
        doctorUpdateMedicalRecordView.menu(doctorId);
    }

    public void viewMedicalRecord(String patientID) {
        MedicalRecord medicalRecord = medicalRecordDAO.loadSingleRecord(patientID);
        medicalRecord.setPastDiagnosesAndTreatments(pastDiagnosesAndTreatmentsController.getPastDiagnosesAndTreatments(patientID));
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
        medicalRecordDAO.addMedicalRecord(medicalRecord);
        System.out.println("Medical record created successfully!");
    }


        public void viewPastAppointments(String patientID) {
        List<AppointmentOutcome> outcomes = appointmentOutcomeController.getAppointmentOutcomeByPatientID(patientID);
        if (outcomes != null && !outcomes.isEmpty()) {
            System.out.println("Displaying past appointments for Patient ID: " + patientID);
            for (AppointmentOutcome outcome : outcomes) {
                appointmentOutcomeController.printAppointmentOutcome(outcome);
            }
        } else {
            System.out.println("No past appointments found for Patient ID: " + patientID);
        }
    }

    public List<MedicalRecord> getMedicalRecordsUnderDoctor(String doctorId) {
        List<AppointmentOutcome> AOLists = doctorController.getAppointmentOutcomeByDoctorId(doctorId);
        List<MedicalRecord> patientsMR = new ArrayList<>();
        Set<String> uniquePatientIDs = new HashSet<>();
        
        for (AppointmentOutcome ao : AOLists) {
            if (ao.getDoctorID().equals(doctorId) && uniquePatientIDs.add(ao.getPatientID())) {
                MedicalRecord medicalRecord = medicalRecordDAO.loadSingleRecord(ao.getPatientID());
                if (medicalRecord != null) { // Ensure the record exists before adding
                    patientsMR.add(medicalRecord);
                }
            }
        }
        return patientsMR;
    }

    public List<String> getPatientsUnderDoctor(String doctorId) {
        List<String> patients = new ArrayList<>();
        for (MedicalRecord mr : getMedicalRecordsUnderDoctor(doctorId)) {
            patients.add(mr.getPatientID());
        }
        return patients;
    }

    public List<AppointmentOutcome> getAppointmentOutcomeByPatientId(String patientId) {
        return doctorController.getAppointmentOutcomeByPatientId(patientId);
    }

    public void createAppointmentOutcome(String date ,String time,String typeOfService,ArrayList<PrescribedMedication> medications, String notes, String doctorId, String patientId, String appointmentId) {
        doctorController.createAppointmentOutcome(date, time, typeOfService, medications, notes, doctorId, patientId, appointmentId);
        pastDiagnosesAndTreatmentsController.addPastDiagnosisAndTreatment(patientId, notes, typeOfService);
    }
}

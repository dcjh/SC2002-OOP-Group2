package Controller;

import Model.Shared.MedicalRecord;
import Model.Shared.PrescribedMedication;
import Model.Shared.AppointmentOutcome;
import Data.DataAccess.MedicalRecordDAO;
import View.MedicalRecord.MedicalRecordView;
import View.Doctor.DoctorMedicalRecordView;
import View.Doctor.DoctorUpdateMedicalRecordView;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The MedicalRecordController class is responsible for managing medical records,
 * including creating, viewing, updating, and retrieving medical records.
 * It also handles interactions related to past diagnoses, treatments, and appointments.
 */
public class MedicalRecordController {

    private MedicalRecordDAO medicalRecordDAO;
    private MedicalRecordView medicalRecordView;
    private DoctorMedicalRecordView doctorMedicalRecordView;
    private DoctorUpdateMedicalRecordView doctorUpdateMedicalRecordView;
    private DoctorController doctorController;
    private AppointmentOutcomeController appointmentOutcomeController;
    private PastDiagnosesAndTreatmentsController pastDiagnosesAndTreatmentsController;

    /**
     * Constructs a MedicalRecordController instance for a specific doctor.
     *
     * @param doctorController the DoctorController instance used for managing doctor-related operations
     */
    public MedicalRecordController(DoctorController doctorController) {
        this.medicalRecordDAO = new MedicalRecordDAO();
        this.medicalRecordView = new MedicalRecordView();
        this.doctorMedicalRecordView = new DoctorMedicalRecordView(this);
        this.doctorUpdateMedicalRecordView = new DoctorUpdateMedicalRecordView(this);
        this.doctorController = doctorController;
        this.pastDiagnosesAndTreatmentsController = new PastDiagnosesAndTreatmentsController();
        this.appointmentOutcomeController = new AppointmentOutcomeController();
        this.pastDiagnosesAndTreatmentsController = new PastDiagnosesAndTreatmentsController();
    }

    /**
     * Default constructor for MedicalRecordController.
     */
    public MedicalRecordController() {
        this.medicalRecordDAO = new MedicalRecordDAO();
        this.medicalRecordView = new MedicalRecordView();
        this.doctorMedicalRecordView = new DoctorMedicalRecordView(this);
        this.doctorUpdateMedicalRecordView = new DoctorUpdateMedicalRecordView(this);
        this.doctorController = null;
        this.pastDiagnosesAndTreatmentsController = new PastDiagnosesAndTreatmentsController();
        this.appointmentOutcomeController = new AppointmentOutcomeController();
        this.pastDiagnosesAndTreatmentsController = new PastDiagnosesAndTreatmentsController();
    }

    /**
     * Navigates to the medical record view for a specific doctor.
     *
     * @param doctorId the ID of the doctor
     */
    public void doctorMedicalRecordView(String doctorId) {
        doctorMedicalRecordView.menu(doctorId);
    }

    /**
     * Navigates to the update medical record view for a specific doctor.
     *
     * @param doctorId the ID of the doctor
     */
    public void updateMedicalRecordView(String doctorId) {
        doctorUpdateMedicalRecordView.menu(doctorId);
    }

    /**
     * Displays the medical record for a specific patient.
     *
     * @param patientID the ID of the patient whose medical record is to be viewed
     */
    public void viewMedicalRecord(String patientID) {
        MedicalRecord medicalRecord = medicalRecordDAO.loadSingleRecord(patientID);
        medicalRecord.setPastDiagnosesAndTreatments(pastDiagnosesAndTreatmentsController.getPastDiagnosesAndTreatments(patientID));
        if (medicalRecord != null) {
            medicalRecordView.fullDisplay(medicalRecord);
        } else {
            System.out.println("Medical record not found for patient ID: " + patientID);
        }
    }

    /**
     * Updates the contact information (phone number and email) for a specific patient's medical record.
     *
     * @param patientID      the ID of the patient
     * @param newPhoneNumber the new phone number to be updated
     * @param newEmail       the new email to be updated
     */
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

    /**
     * Creates a new medical record in the system.
     *
     * @param medicalRecord the MedicalRecord object to be created
     */
    public void createMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordDAO.addMedicalRecord(medicalRecord);
        System.out.println("Medical record created successfully!");
    }

    /**
     * Displays past appointments for a specific patient.
     *
     * @param patientID the ID of the patient whose past appointments are to be viewed
     */
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

    /**
     * Retrieves medical records for all patients under the care of a specific doctor.
     *
     * @param doctorId the ID of the doctor
     * @return a list of MedicalRecord objects for patients under the care of the specified doctor
     */
    public List<MedicalRecord> getMedicalRecordsUnderDoctor(String doctorId) {
        List<AppointmentOutcome> AOLists = doctorController.getAppointmentOutcomeByDoctorId(doctorId);
        List<MedicalRecord> patientsMR = new ArrayList<>();
        Set<String> uniquePatientIDs = new HashSet<>();

        for (AppointmentOutcome ao : AOLists) {
            if (ao.getDoctorID().equals(doctorId) && uniquePatientIDs.add(ao.getPatientID())) {
                MedicalRecord medicalRecord = medicalRecordDAO.loadSingleRecord(ao.getPatientID());
                if (medicalRecord != null) {
                    patientsMR.add(medicalRecord);
                }
            }
        }
        return patientsMR;
    }

    /**
     * Retrieves a list of patient IDs for all patients under the care of a specific doctor.
     *
     * @param doctorId the ID of the doctor
     * @return a list of patient IDs for patients under the care of the specified doctor
     */
    public List<String> getPatientsUnderDoctor(String doctorId) {
        List<String> patients = new ArrayList<>();
        for (MedicalRecord mr : getMedicalRecordsUnderDoctor(doctorId)) {
            patients.add(mr.getPatientID());
        }
        return patients;
    }

    /**
     * Retrieves appointment outcomes for a specific patient.
     *
     * @param patientId the ID of the patient
     * @return a list of AppointmentOutcome objects for the specified patient
     */
    public List<AppointmentOutcome> getAppointmentOutcomeByPatientId(String patientId) {
        return doctorController.getAppointmentOutcomeByPatientId(patientId);
    }

    /**
     * Creates an appointment outcome for a specific patient and adds it to the system.
     *
     * @param date         the date of the appointment outcome
     * @param time         the time of the appointment outcome
     * @param typeOfService the type of service provided
     * @param medications  a list of prescribed medications
     * @param notes        the consultation notes
     * @param doctorId     the doctor's ID
     * @param patientId    the patient's ID
     * @param appointmentId the appointment ID
     */
    public void createAppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> medications, String notes, String doctorId, String patientId, String appointmentId) {
        doctorController.createAppointmentOutcome(date, time, typeOfService, medications, notes, doctorId, patientId, appointmentId);
        pastDiagnosesAndTreatmentsController.addPastDiagnosisAndTreatment(patientId, notes, typeOfService);
    }
}

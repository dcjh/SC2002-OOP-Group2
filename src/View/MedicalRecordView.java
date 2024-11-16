package View;

import Model.Shared.MedicalRecord;
import Model.Shared.AppointmentOutcomeRecord;

public class MedicalRecordView {

    public void menu(MedicalRecord medicalRecord) {
        System.out.println("Medical Record:");
        System.out.println("Patient ID: " + medicalRecord.getPatientID());
        System.out.println("Name: " + medicalRecord.getName());
        System.out.println("Date of Birth: " + medicalRecord.getDob());
        System.out.println("Gender: " + medicalRecord.getGender());
        System.out.println("Phone Number: " + medicalRecord.getPhoneNumber());
        System.out.println("Email: " + medicalRecord.getEmail());
        System.out.println("Blood Type: " + medicalRecord.getBloodType());
        System.out.println("Allergies: " + medicalRecord.getAllergies());
        System.out.println("\nPast Diagnoses and Treatments:");
        for (AppointmentOutcomeRecord outcome : medicalRecord.getAppointmentOutcome()) {
            System.out.println("Diagnoses: " + outcome.getDiagnoses());
            System.out.println("Treatments: " + outcome.getTreatments());
            System.out.println();
        }
    }
}

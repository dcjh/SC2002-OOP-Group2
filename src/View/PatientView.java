public class PatientView {
    public void fullDisplay(MedicalRecord medicalRecord) {
        System.out.println("Medical Record:");
        System.out.println("Patient ID: " + medicalRecord.getPatientID());
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
            System.out.println(); // Blank line for readability between records
        }
    }

    public void semiDisplay(MedicalRecord medicalRecord) {
        System.out.println("Past Appointment Outcome Records:");

        for (AppointmentOutcomeRecord outcome : medicalRecord.getAppointmentOutcome()) {
            System.out.println("Diagnoses: " + outcome.getDiagnoses());
            System.out.println("Service Provided: " + outcome.getTreatments());
            System.out.println("Prescribed Medications: " + outcome.getPrescribedMedications());
            System.out.println("Consultation Notes: " + outcome.getConsultationNotes());
            System.out.println("Update Timestamp: " + outcome.getUpdateTimestamps());
            System.out.println();
        }
    }

}

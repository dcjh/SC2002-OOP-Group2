package View;

import Model.Shared.AppointmentOutcomeRecord;

import java.util.List;

public class PastAppointmentView {
    // Display past appointment outcomes
    public void displayPastAppointments(List<AppointmentOutcomeRecord> appointmentOutcomes) {
        System.out.println("Past Appointment Outcome Records:");
        for (AppointmentOutcomeRecord outcome : appointmentOutcomes) {
            System.out.println("Diagnoses: " + outcome.getDiagnoses());
            System.out.println("Service Provided: " + outcome.getTreatments());
            System.out.println("Prescribed Medications: " + outcome.getPrescribedMedications());
            System.out.println("Consultation Notes: " + outcome.getConsultationNotes());
            System.out.println("Update Timestamp: " + outcome.getUpdateTimestamps());
            System.out.println();
        }
    }
}

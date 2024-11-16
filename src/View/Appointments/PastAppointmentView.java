package View.Appointments;

import Model.Shared.AppointmentOutcome;

import java.util.List;

public class PastAppointmentView {
    // Display past appointment outcomes
    public void displayPastAppointments(List<AppointmentOutcome> appointmentOutcomes) {
        System.out.println("Past Appointment Outcome Records:");
        for (AppointmentOutcome outcome : appointmentOutcomes) {
            System.out.println("Diagnoses: " + outcome.getDiagnoses());
            System.out.println("Service Provided: " + outcome.getTreatments());
            System.out.println("Prescribed Medications: " + outcome.getPrescribedMedications());
            System.out.println("Consultation Notes: " + outcome.getConsultationNotes());
            System.out.println("Update Timestamp: " + outcome.getUpdateTimestamps());
            System.out.println();
        }
    }
}

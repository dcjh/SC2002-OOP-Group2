package View;

import Model.Shared.PrescribedMedication;
import java.util.ArrayList;

public class AppointmentOutcomeView {

    public void printAppointmentOutcome(String AppointmentOutcomeID, String date, String time, String typeOfService, ArrayList<PrescribedMedication> prescribedMedications, String consultationNotes) {
    System.out.printf("%-25s : %s%n", "Appointment Outcome ID", AppointmentOutcomeID);
    System.out.printf("%-25s : %s%n", "Date", date);
    System.out.printf("%-25s : %s%n", "Time", time);
    System.out.printf("%-25s : %s%n", "Type of Service", typeOfService);
    System.out.printf("%-25s : %s%n", "Consultation Notes", consultationNotes);
    
    /*
    System.out.printf("%-20s : ", "Prescribed Medications");
    if (prescribedMedications.isEmpty()) {
        System.out.println("None");
    } else {
        System.out.println();
        for (PrescribedMedication medication : prescribedMedications) {
            System.out.printf("  - %s%n", medication.toString());
        }
    }
    */

    System.out.printf("%-25s :%n", "Prescribed Medications");
    if (prescribedMedications.isEmpty()) {
        System.out.println("None");
    } else {
        System.out.printf("%-20s %-15s %-10s%n", "Medicine Name", "Quantity", "Status");
        System.out.println("--------------------------------------------------");
        for (PrescribedMedication medication : prescribedMedications) {
            System.out.printf("%-20s %-15d %-10s%n",
                    medication.getMedicineName(), // Assuming Medicine has getMedicineName()
                    medication.getQuantity(),     // Assuming Medicine has getQuantity()
                    medication.getStatus());
        }
    }
}

}

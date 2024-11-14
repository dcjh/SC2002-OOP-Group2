package View;

import Model.Shared.PrescribedMedication;
import java.util.ArrayList;

public class AppointmentOutcomeView {

    public void printAppointmentOutcome(String date, String time, String typeOfService, ArrayList<PrescribedMedication> prescribedMedications, String consultationNotes) {
    System.out.printf("%-20s : %s%n", "Date", date);
    System.out.printf("%-20s : %s%n", "Time", time);
    System.out.printf("%-20s : %s%n", "Type of Service", typeOfService);
    
    System.out.printf("%-20s : ", "Prescribed Medications");
    if (prescribedMedications.isEmpty()) {
        System.out.println("None");
    } else {
        System.out.println();
        for (PrescribedMedication medication : prescribedMedications) {
            System.out.printf("  - %s%n", medication.toString());
        }
    }
    
    System.out.printf("%-20s : %s%n", "Consultation Notes", consultationNotes);
}

}

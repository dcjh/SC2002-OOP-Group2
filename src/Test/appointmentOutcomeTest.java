package Test;

import Controller.AppointmentOutcomeController;
import Model.Shared.AppointmentOutcome;
import Model.Shared.PrescribedMedication;
import java.util.ArrayList;

public class appointmentOutcomeTest {
    public static void main(String[] args) {
        ArrayList<PrescribedMedication> prescribedMedications = new ArrayList<>();
        prescribedMedications.add(new PrescribedMedication("panadol", 20));
        prescribedMedications.add(new PrescribedMedication("cough syrup", 22));
        prescribedMedications.add(new PrescribedMedication("h2o", 19));

        AppointmentOutcome aptOutcome = new AppointmentOutcome("12-10-2003", "10:30", "X-Ray", prescribedMedications, "Yoooo");

        AppointmentOutcomeController aptOutcomeController = new AppointmentOutcomeController(aptOutcome);

        System.out.println(aptOutcomeController.getDate());
        System.out.println(aptOutcomeController.getTime());
        System.out.println(aptOutcomeController.getTypeOfService());
        System.out.println(aptOutcomeController.getConsultationNotes());
        
        aptOutcomeController.printAppointmentOutcome();
        
        aptOutcomeController.setStatusDispensed("panadol");

        aptOutcomeController.printAppointmentOutcome();

    }
}

package Controller;


import java.time.LocalDate;
import java.util.List;
import Model.Shared.MedicalRecord;

public class PatientController {
    private PatientView view;
    private MedicalRecord medicalRecord;
    public PatientController(MedicalRecord medicalRecord, PatientView view) {
        this.view = view;
        this.medicalRecord=medicalRecord;
    }
    public void updateContactInformation(String newPhoneNumber, String newEmail) {
        medicalRecord.setPhoneNumber(newPhoneNumber);
        medicalRecord.setEmail(newEmail);
        System.out.println("Contact information updated successfully.");
    }

    public void viewMedicalRecord() {
        view.fullDisplay(medicalRecord);
    }

    public void viewPastAppointment() {
        view.semiDisplay(medicalRecord);
    }

}

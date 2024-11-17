package View.Doctor;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import Controller.MedicalRecordController;
import Model.Shared.MedicalRecord;
import Model.Shared.AppointmentOutcome;

public class DoctorMedicalRecordView {
 
    private MedicalRecordController medicalRecordController;

    public DoctorMedicalRecordView() {}

    public DoctorMedicalRecordView (MedicalRecordController medicalRecordController) {
        this.medicalRecordController = medicalRecordController;
    }

    
    /** 
     * @param doctorId
     */
    public void menu(String doctorId) {
        Scanner scanner = new Scanner(System.in);
        String linebr = "----------------------------------------------";

        List<MedicalRecord> patientsMR = medicalRecordController.getMedicalRecordsUnderDoctor(doctorId);

        if (patientsMR.isEmpty()) {
            System.out.println("No past patients :(");
            return;
        }

        while(true){
            System.out.println(linebr);
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Return to main menu");
            System.out.println(linebr);
            System.out.print("Enter Choice:");
            int proceed;

            try {
                proceed = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (proceed) {
                case 1:
                    viewPatientMR(patientsMR,scanner);
                    break;
                case 2:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again...");
            }
        }
    }

    
    /** 
     * @param patientsMR
     */
    protected void printPatientList(List<MedicalRecord> patientsMR) {
        String border = "+------------+----------------------+---------------+--------+-------------------------------------+";
        String header = "| Patient ID |         Name         | Date of Birth | Gender |                Email                |";
    
        System.out.println(border);
        System.out.println(header);
        System.out.println(border);
    
        for (MedicalRecord a : patientsMR) {
            System.out.printf("| %-10s | %-20s | %-13s | %-6s | %-35s |%n",
                    a.getPatientID(),
                    a.getName(),
                    a.getDob(),
                    a.getGender(),
                    a.getEmail());
            System.out.println(border);
        }
    }

    private void viewPatientMR(List<MedicalRecord> patientsMR, Scanner scanner) {
        String linebr = "----------------------------------------------";
        MedicalRecord selectedPatient = null;
        String patientId;

        printPatientList(patientsMR);
        System.out.println(linebr);
        System.out.println("Input patient's medical record to view");
        while(selectedPatient == null){
            System.out.print("Enter Patient Id:");
            patientId = scanner.nextLine();

            for (MedicalRecord mr : patientsMR) {
                if (mr.getPatientID().equals(patientId)) {
                    selectedPatient = mr;
                    break;
                }
            }
            if (selectedPatient == null) System.out.println("Invalid Patient ID. Please try again.");
        }
        printPatientMedicalReport(selectedPatient);
    }

    protected void printPatientMedicalReport(MedicalRecord medicalRecord) {
        String border = "+---------------+-------------------------------------------------+";
        System.err.println(border);
        System.out.printf("| Patient ID    |" + " %-45s   |%n",medicalRecord.getPatientID());
        System.out.printf("| Date of Birth |" + " %-45s   |%n",medicalRecord.getDob());
        System.out.printf("| Gender        |" + " %-45s   |%n",medicalRecord.getGender());
        System.out.printf("| Phone Number  |" + " %-45s   |%n",medicalRecord.getPhoneNumber());
        System.out.printf("| Email         |" + " %-45s   |%n",medicalRecord.getEmail());
        System.out.printf("| Blood Type    |" + " %-45s   |%n",medicalRecord.getBloodType());
        System.out.printf("| Allergies     |" + " %-45s   |%n",medicalRecord.getAllergies());
        System.err.println(border);
        System.out.println("|                         Date of Record                          |");

        List<AppointmentOutcome> aoList = medicalRecordController.getAppointmentOutcomeByPatientId(medicalRecord.getPatientID());
        for (AppointmentOutcome a : aoList) {
            System.err.println(border);
            System.out.printf("| Date          |" + " %-45s   |%n",a.getDate());
            System.err.println(border);
            System.out.printf("| Diagnosis     |" + " %-45s   |%n",a.getConsultationNotes());
            System.out.printf("| Treatment     |" + " %-45s   |%n",a.getTypeOfService());
            for (int i=0; i<a.getPrescribedMedications().size(); i++) {
                if(i==0) {
                    System.out.printf("| Medication    |" + " %-45s   |%n",a.getPrescribedMedications().get(i));
                } else {
                    System.out.printf("|               |" + " %-45s   |%n",a.getPrescribedMedications().get(i));
                }
            }
            System.err.println(border);
        }

    }

}

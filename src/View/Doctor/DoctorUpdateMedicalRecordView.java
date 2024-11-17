package View.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.MedicalRecordController;
import Model.Shared.MedicalRecord;
import Model.Shared.PrescribedMedication;
import Model.Shared.AppointmentOutcome;

public class DoctorUpdateMedicalRecordView extends DoctorMedicalRecordView{
    
    private MedicalRecordController medicalRecordController;

    public DoctorUpdateMedicalRecordView(MedicalRecordController medicalRecordController) {
        super(medicalRecordController);
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
            System.out.println("1. Update Patient Medical Records");
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
                    updatePatientMR(patientsMR,scanner,doctorId);
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
     * @param scanner
     * @param doctorId
     */
    protected void updatePatientMR(List<MedicalRecord> patientsMR, Scanner scanner, String doctorId) {
        String linebr = "----------------------------------------------";
        MedicalRecord selectedPatient = null;
        String patientId;

        super.printPatientList(patientsMR);
        System.out.println(linebr);
        System.out.println("Input patient's medical record to update");
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
        choosePatient(selectedPatient, scanner, doctorId);
    }

    protected void choosePatient(MedicalRecord selectedPatientMR, Scanner scanner, String doctorId) {
        String linebr = "----------------------------------------------";
        AppointmentOutcome selectedAO = null;
        String appointment;

        System.out.println(linebr);
        super.printPatientMedicalReport(selectedPatientMR);
        System.out.println(linebr);
    
        String newDate;
        while (true) {
            System.out.print("Enter new date (dd-mm-yyyy): ");
            newDate = scanner.nextLine().trim();
            if (newDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                break;
            }
            System.out.println("Invalid date format. Please try again.");
        }
        
        // Capture and validate new time
        String newTime;
        while (true) {
            System.out.print("Enter new time (HH:mm): ");
            newTime = scanner.nextLine().trim();
            if (newTime.matches("\\d{2}:\\d{2}")) {
                break;
            }
            System.out.println("Invalid time format. Please try again.");
        }

        // Capture and validate new diagnosis
        String newDiagnosis;
        while (true) {
            System.out.print("Enter new diagnosis: ");
            newDiagnosis = scanner.nextLine();
            if (!newDiagnosis.isEmpty()) {
                break;
            }
            System.out.println("Diagnosis cannot be empty. Please try again.");
        }
    
        // Capture and validate new treatment plan
        String newTreatment;
        while (true) {
            System.out.print("Enter new treatment plan: ");
            newTreatment = scanner.nextLine();
            if (!newTreatment.isEmpty()) {
                break;
            }
            System.out.println("Treatment plan cannot be empty. Please try again.");
        }

        // Add in new medications
        ArrayList<PrescribedMedication> medications = new ArrayList<>();
        System.out.println("Input the prescribed medication and its quantity ");
        System.out.println(linebr);
        while (true) {
            System.out.println("Enter medicine name (type 'n' to stop):");
            String medicineName = scanner.nextLine(); // Read the input
            if (medicineName.equalsIgnoreCase("n")) { // Check if the input is "n" (case-insensitive)
                break; // Exit the loop
            }
            System.out.println("Enter quantity of "+ medicineName + " : ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            medications.add(new PrescribedMedication(medicineName, quantity));
        }
    
        // Print the new appointment outcome for confirmation
        System.out.println(linebr);
        System.out.println("Updated Appointment Outcome Preview:");
        System.out.println(linebr);
        System.out.printf("Date         : %s%n", newDate);
        System.out.printf("Time         : %s%n", newTime);
        System.out.printf("Doctor ID    : %s%n", doctorId);
        System.out.printf("Patient ID   : %s%n", selectedPatientMR.getPatientID());
        System.out.printf("Diagnosis    : %s%n", newDiagnosis);
        System.out.printf("Treatment    : %s%n", newTreatment);

        for (int i=0; i<medications.size(); i++) {
            if(i==0) {
                System.out.printf("Medication   : %s%n",medications.get(i));
            } else {
                System.out.printf("               %s%n",medications.get(i));
            }
        }

        System.out.println(linebr);
    
        // Confirmation loop
        while (true) {
            System.out.print("Do you want to save these changes? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (confirmation.equals("yes")) {
                break; // Proceed with saving
            } else if (confirmation.equals("no")) {
                System.out.println("Update canceled. No changes were made.");
                return;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    
        // Update the record in the MedicalRecordController
        medicalRecordController.createAppointmentOutcome(newDate,
                                                        newTime,
                                                        newTreatment,
                                                        medications,
                                                        newDiagnosis,
                                                        doctorId,
                                                        selectedPatientMR.getPatientID(),
                                                        "-");

        System.out.println(linebr);
        System.out.println("Updated Medical Record");
        System.out.println(linebr);
        super.printPatientMedicalReport(selectedPatientMR);
    }

}

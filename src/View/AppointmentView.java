package View;

public class AppointmentView {
    public void printAppointment(String docID, String patientID, String status, String time, String date){
        System.out.printf("%-15s : %s%n", "Doctor ID", docID);
        System.out.printf("%-15s : %s%n", "Patient ID", patientID);
        System.out.printf("%-15s : %s%n", "Status", status);
        System.out.printf("%-15s : %s%n", "Date", date);
        System.out.printf("%-15s : %s%n", "Time", time);
    }
    
}

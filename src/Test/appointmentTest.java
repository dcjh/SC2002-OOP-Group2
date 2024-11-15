package Test;

import Controller.AppointmentController;
import Model.Shared.Appointment;

public class appointmentTest {
    public static void main(String[] args) {
        Appointment apt = new Appointment("0001", "0002", "10:30", "12-10-2024");
        AppointmentController aptController = new AppointmentController(apt);

        System.out.println(aptController.getPatientID());
        System.out.println(aptController.getDocID());
        System.out.println(aptController.getStatus());
        System.out.println(aptController.getDate());
        System.out.println(aptController.getTime());

        aptController.setStatus("approved");
        aptController.setDate("13-10-2024");
        aptController.setTime("05:30");
        aptController.printAppointment();
    }
}
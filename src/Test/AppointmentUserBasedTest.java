package Test;

import Controller.AppointmentController;

public class AppointmentUserBasedTest {
    public static void main(String[] args) {
        AppointmentController aptController = new AppointmentController();

        //made by Patient P1001
        // patient made 2 appointments with Doctor D001 and D002 then view to see its status still pending
        aptController.createAppointment("D001", "P1001", "10:30", "12-10-2023");
        aptController.createAppointment("D002", "P1001", "11:30", "13-10-2023");
        aptController.viewAppointmentsByPatientID("P1001");

        //made by Patient P1002
        // patient made 3 appointments, 2 with Doctor D001 and 1 with D002 then view to see its status still pending
        aptController.createAppointment("D001", "P1002", "12:30", "14-10-2023");
        aptController.createAppointment("D002", "P1002", "13:30", "15-10-2023");
        aptController.createAppointment("D001", "P1002", "13:30", "15-10-2023");
        aptController.viewAppointmentsByPatientID("P1002");

        //made by Doctor D001
        // doctor D001 view all of his appointments that are pending
        // approved appointment request from P1002 and rejected/cancelled the request from P1001
        // doctor view all of his appointments that are approved
        // doctor view all of his appointments regardless of status
        aptController.viewPendingAppointmentsByDoctorID("D001");
        aptController.updateAppointmentStatus("AP0003", "approved");
        aptController.updateAppointmentStatus("AP0001", "cancelled");
        aptController.viewApprovedAppointmentsByDoctorID("D001");

        //made by Patient P1002
        // patient see his approved upcoming appointment
        // patient see all of his other appointment requests
        // patient reschedules his appointment with D1001
        // patient views all of his appointments
        aptController.viewApprovedAppointmentsByPatientID("P1002");
        aptController.viewAppointmentsByPatientID("P1002");
        aptController.updateAppointmentReschedule("AP0003", "12-12-2012", "12:12");
        aptController.viewApprovedAppointmentsByPatientID("P1002");
        aptController.viewAppointmentsByPatientID("P1002");

         //made by Doctor D001
         aptController.viewPendingAppointmentsByDoctorID("D001");

        //made by Admin
        aptController.viewAllAppointment();

    }
}

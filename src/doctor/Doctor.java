package doctor;

import user.User;
import doctor.*;
import java.util.ArrayList;
import java.util.List;
import dataClasses.*;

public class Doctor extends User{
    
    private List<Appointment> appointmentRequests;
    private List<Appointment> confirmedAppointments;
    private List<Availability> availabilityDates ;
    private List<MedicalRecord> patients;


    public Doctor(String staffID, String password, String role, String name, String gender, int age) {
        super(staffID, password, role, name, gender, age);
        this.appointmentRequests = new ArrayList<>();
        this.confirmedAppointments = new ArrayList<>();
        this.availabilityDates = new ArrayList<>();
        this.patients = new ArrayList<>();
    }
   
    // data mgmt for appointment requests
    public List<Appointment> getAppointmentRequests() { /*change this to type appointment once implemented*/
        return appointmentRequests;
    }
    public void addAppointmentRequests() { /*change this to type appointment once implemented*/
        appointmentRequests.remove();
    }
    



    public List<Appointment> getAppointments() { /*change this to type appointment once implemented*/
        return confirmedAppointments;
    }
    public void Appointments() { /*change this to type appointment once implemented*/
        
    }

    public List<String /*type medicalRecord */> getPatients() {
        return patients;
    }
    public void addPatients(String medicalRecord) {
        patients.add(medicalRecord);
    }

    public List<Long> getAvailabilityDates() {
        return availabilityDates;
    }

}










    // public void viewMenu() {
    //     System.out.println();
    //     System.out.println("Doctor Menu:");
    //     System.out.println("1. View Schedule");
    //     System.out.println("2. View Appointment Requests");
    //     System.out.println("3. Patient Records");
    //     System.out.println("4. Logout");
    // }

    // public void getAllAppointment() {
    //     System.out.println("Appointment List:");
    //     for (int i = 0; i < appointments.size(); i++) {
    //         System.out.println("-----------------------------");
    //         System.out.println("Patient"  + (i + 1) + ":"); 
    //         System.out.println(appointments.get(i));
    //     }
    // }

    // public void getAppointment(String patientID) {
    //     for (int i = 0; i < appointments.size(); i++) {
    //         if (appointments.get(i).patientID == patientID) {
    //             System.out.println("Appointment:");
    //             System.out.println("-----------------------------");
    //             System.out.println(appointments.get(i));
    //             System.out.println("-----------------------------");
    //             break;
    //         }
    //     }
    // }

    // public void getAvailability() {
    //     System.out.println("Appointment List:");
    //     for (int i = 0; i < appointments.size(); i++) {
    //         System.out.println("-----------------------------");
    //         System.out.println("Patient"  + (i + 1) + ":"); 
    //         System.out.println(appointments.get(i));
    //     }
    // }

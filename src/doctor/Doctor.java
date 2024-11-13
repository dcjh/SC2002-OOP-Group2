package Doctor;

import java.util.ArrayList;
import java.util.List;

import Repository.dataClasses.*;
import User.User;

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
    public List<Appointment> getAppointmentRequests() {
        return appointmentRequests;
    }
    public void addAppointmentRequests(Appointment request) {
        appointmentRequests.add(request);
    }
    public void removeAppointmentRequests(Appointment request) {
        appointmentRequests.remove(request);
    }

    //data mgmt for confirmed appointments
    public List<Appointment> getAppointments() {
        return confirmedAppointments;
    }
    public void addAppointment(Appointment appointment) {
        confirmedAppointments.add(appointment);
    }
    public void removeAppointment(Appointment appointment) {
        confirmedAppointments.remove(appointment);
    }

    //data mgmt for patients
    public List<MedicalRecord> getPatients() {
        return patients;
    }
    public void addPatient(MedicalRecord medicalRecord) {
        patients.add(medicalRecord);
    }
    public void removePatient(MedicalRecord medicalRecord) {
        patients.remove(medicalRecord);
    }

    //data mgmt for availability
    public List<Availability> getAvailabilityDates() {
        return availabilityDates;
    }
    public void addAvailabilityDate(Availability availability) {
        availabilityDates.add(availability);
    }
    public void removeAvailabilityDate(Availability availability) {
        availabilityDates.remove(availability);
    }

}
package Model.Roles;

import java.util.ArrayList;
import java.util.List;

import Model.Shared.Appointment;
import Model.Shared.MedicalRecord;
import Model.Shared.Schedule;
import Model.Shared.User;

public class Doctor extends User{
    
    private static UserType userType;
    private String name;
    private Gender gender;
    private int age;

    public Doctor(String hosID, String password,UserType userType, String name, Gender gender, int age) {
        super(hosID, password,UserType.DOCTOR , name, Gender.MALE, age);
    }
   
    public String getDoctorId() {
        return hosID;
    }
    public void setDoctorId(String hosID) {
        this.hosID = hosID;
    }

    public String getPassword() {
        return password;
        //using auth
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


}
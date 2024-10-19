package doctor;
import user.User; 

public class Doctor extends User{
    
    private String name;
    private String role;
    private String gender;
    private int age;

    public Doctor(String staffID, String password, String role, String gender, int age) {
        super(staffID, password);
        this.role = role;
        this.gender = gender;
        this.age = age;
    }

    public void viewMenu() {
        System.out.println();
        System.out.println("Doctor Menu:");
        System.out.println("1. View Schedule");
        System.out.println("2. View Appointment Requests");
        System.out.println("3. Patient Records");
        System.out.println("4. Logout");
    }

    public String getStaffID() {
        return this.getUserID();  // Return the userID from User class as staffID
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void viewSchedule() {

    }

    public void viewPersonalSchedule() {

    }


    public void patientRecords() {

    }



}

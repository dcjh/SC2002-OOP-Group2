package user;
//this is here for temp/ref usage, more time will be spend to make this better
public abstract class User {
    protected String hosID;
    protected String password;
    private String role;
    private String name;
    private String gender;
    private int age;

    // Constructor
    public User(String hosID, String password, String role, String name, String gender, int age) {
        this.hosID = hosID;
        this.password = password;
        this.role = role;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // Common behavior: Login
    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // Getters and Setters
    public String gethosID() {
        return hosID;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
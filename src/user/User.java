package user;
//this is here for temp/ref usage, more time will be spend to make this better
public abstract class User {
    protected String userID;
    protected String password;

    // Constructor
    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    // Common behavior: Login
    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // Abstract methods to be implemented by subclasses
    public abstract void viewMenu(); // Each user will have a different menu

    // Getters and Setters
    public String getUserID() {
        return userID;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
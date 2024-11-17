package Model.Shared;

import Model.Roles.UserType;
import Model.Roles.Gender;

//this is here for temp/ref usage, more time will be spend to make this better
public abstract class User {
    protected String hosID;
    protected String password;
    protected String role;
    protected String name;
    protected Gender gender;
    protected int age;
    protected UserType userType;

    
    // Constructor
    public User(String hosID, String password, UserType userType, String name, Gender gender, int age) {
        this.hosID = hosID;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.gender = gender;
        this.age = age;

    }
    
    /** 
     * @return String
     */
    // Getters and Setters
    public String getName() {
        return name;
    }

    
    /** 
     * @param newName
     */
    public void setName(String newName) {
        this.name = newName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender newGender) {
        this.gender = newGender;
    }

    public String gethosID() {
        return hosID;
    }

    public void sethosID(String newhosID) {
        this.hosID = newhosID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

        public UserType getUserType() {
        return userType;
    }
    
    public String getRole() {
        return role;
    }
    
    public String getPassword() {
    	return password;
    }
    

	public void setPassword(String newPassword) {
		this.password = newPassword;
	} 

        
    // Common behavior: Login
    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }

}

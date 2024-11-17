package Model.Shared;
//this is here for temp/ref usage, more time will be spend to make this better
public abstract class User {
    protected String hosID;
    protected String password;
    private String role;
    private String name;
    private String gender;
    private int age;
    private UserType userType;

    // Constructor
    public User(String hosID, String password, UserType userType, String name, String gender, int age) {
        this.hosID = hosID;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.userType = userType;
        
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String newGender) {
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

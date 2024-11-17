package Model.Roles;

import Model.Shared.User;

public class Administrator extends User{
    
	public Administrator(String hosID, String password,UserType userType, String name, Gender gender, int age) {
        super(hosID, password, UserType.ADMINISTRATOR , name, gender, age);
    }

    
    /** 
     * @return String
     */
    public String getAdministratorId() {
        return hosID;
    }
    
    /** 
     * @param hosID
     */
    public void setAdministratorId(String hosID) {
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

package Model.Roles;

public class Administrator extends User{
    
	public Administrator(String hosID, String password,UserType userType, String name, String gender, int age) {
        super(hosID, password,UserType.ADMINISTRATOR , name, gender, age);
    }
    
}

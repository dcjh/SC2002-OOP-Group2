/*in main uml and entity uml
contains
+ gethosID()
+ sethosID()
+ getPassword()
+ setPassword()
extends from <<abstract>>user, related to PharmacistRepository 
used by PharmacistController
*/
package Model.Roles;

import java.util.ArrayList;
import java.util.List;

import Model.Shared.Appointment;
import Model.Shared.User;


public class Pharmacist extends User{
	public Pharmacist(String hosID, String password,UserType userType, String name, Gender gender, int age) {
        super(hosID, password,UserType.PHARMACIST, name, gender, age);
    }

    public String getPharmacistId() {
        return hosID;
    }
    public void setPharmacistId(String hosID) {
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

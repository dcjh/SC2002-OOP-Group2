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
import User.User;

public class Pharmacist extends User{
    public Pharmacist(String staffID, String password, String role, String name, String gender, int age){
        super(staffID, password, role, name, gender, age);
    }
}

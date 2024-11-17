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

import Data.DataClasses.*;
import Model.Shared.Appointment;
import Model.Shared.User;


public class Pharmacist extends User{
	public Pharmacist(String hosID, String password,UserType userType, String name, String gender, int age) {
        super(hosID, password,UserType.PHARMACIST, name, gender, age);
    }
}

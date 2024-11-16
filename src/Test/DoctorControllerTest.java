package Test;
import Controller.DoctorController;

public class DoctorControllerTest {
    
    private static DoctorController doctorController;

    public static void main(String args[]) {
        doctorController = new DoctorController();
        doctorController.displayDoctorView("D0002");
    }
}

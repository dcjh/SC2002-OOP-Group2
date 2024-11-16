package Test;

import java.util.*;
import Controller.DoctorController;
import Controller.ScheduleController;

public class DoctorControllerTest {
    
    private static DoctorController doctorController;

    public static void main(String args[]) {
        doctorController = new DoctorController();
        doctorController.viewDoctorSchedule("D0002");
        doctorController.displayDoctorView("D0008");
    }
}

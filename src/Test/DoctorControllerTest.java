package Test;

import java.util.*;
import Controller.DoctorController;
import Controller.ScheduleController;

public class DoctorControllerTest {
    
    private static DoctorController doctorController;
    private static ScheduleController scheduleController;

    public static void main(String args[]) {
        doctorController = new DoctorController();
        doctorController.viewDoctorSchedule("D0002");
        doctorController.setAvailability("D0007");
    }
}

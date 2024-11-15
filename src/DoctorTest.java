import Controller.DoctorController;
import Service.AvailabilityService;
import Service.DoctorService;
import View.Doctor.DoctorView;

import java.io.ByteArrayInputStream;

public class DoctorTest {

    public static void main(String[] args) {
        // Setup: Initialize the services, controller, and view
        AvailabilityService availabilityService = new AvailabilityService();
        DoctorService doctorService = new DoctorService(availabilityService);
        DoctorController doctorController = new DoctorController(doctorService);
        DoctorView doctorView = new DoctorView(doctorController);

        // Run each test method independently
        testViewPersonalSchedule(doctorView);
        testSetAvailability(doctorView);
        testViewAvailableSlots(doctorView);
    }

    private static void testViewPersonalSchedule(DoctorView doctorView) {
        System.out.println("Testing View Personal Schedule...");

        // String simulatedInput = "3\n1\n4\n7\n"; // Enter Availability menu -> View Personal Schedule -> Back -> Logout

        // Call the menu to test viewing the personal schedule
        doctorView.showMenu("D0001");

        System.out.println("Completed: View Personal Schedule\n");
    }

    private static void testSetAvailability(DoctorView doctorView) {
        System.out.println("Testing Set Availability...");

        // String simulatedInput = "3\n2\n2024-11-21\n1\ntrue\n4\n7\n";

        // Call the menu to test setting the availability
        doctorView.showMenu("D0001");

        System.out.println("Completed: Set Availability\n");
    }

    private static void testViewAvailableSlots(DoctorView doctorView) {
        System.out.println("Testing View Available Slots...");

        // Simulate input with LoggingInputStream
        // String simulatedInput = "3\n3\n2024-11-21\n4\n7\n"; 
        // System.setIn(new LoggingInputStream(simulatedInput.getBytes()));

        // Call the menu to test viewing available slots
        doctorView.showMenu("D0001");

        System.out.println("Completed: View Available Slots\n");
    }
}

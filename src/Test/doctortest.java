package Test;

import DataAccess.ScheduleDAO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import Model.Shared.Schedule;

public class doctortest {
    private static ScheduleDAO data = new ScheduleDAO();
    public static void main(String args[]){

        System.out.println("testing add.......");
        HashMap<LocalDate, Boolean> Avail = new HashMap<>();
        Avail.put(LocalDate.of(2024, 11, 20),true);
        Schedule test1 = new Schedule("D0001",Avail);
        Schedule test2 = new Schedule("D0002",Avail);
        data.add(test1);
        data.add(test2);

        System.out.println("testing find.......");
        Schedule k = data.find("D0001");
        System.out.println(k.getDoctorId() + k.getDateAvailability());

        System.out.println("testing fetch.....");
        List<Schedule> scheduleList = data.fetch();
        for (Schedule s : scheduleList) {
            System.out.println(s.getDoctorId() + s.getDateAvailability());
        }

        System.out.println("testing delete.......");
        data.deleteDate("D0001", LocalDate.of(2024, 11, 20));
        scheduleList = data.fetch();
        for (Schedule s : scheduleList) {
            System.out.println(s.getDoctorId() + s.getDateAvailability());
        }

        System.out.println("testing update avail.......");
        data.updateIsAvailable("D0002", LocalDate.of(2024, 11, 20), false);
        Schedule l = data.find("D0002");
        System.out.println(l.getDoctorId() + l.getDateAvailability());

    }
}

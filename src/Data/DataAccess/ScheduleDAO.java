package Data.DataAccess;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Shared.Schedule;

public class ScheduleDAO {

    private static final String FILE_PATH = "src/Data/Assets/Schedule.csv";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public List<Schedule> fetch() {
        List<Schedule> scheduleList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;  // Skip header
                }
                String[] values = line.split(",");
                if (values.length != 3) { // Ensure exactly 3 fields
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }
                String doctorId = values[0];
                LocalDate date = LocalDate.parse(values[1], DATE_FORMAT);
                Boolean isAvailable = Boolean.parseBoolean(values[2]);
                HashMap<LocalDate, Boolean> dateAvailability = new HashMap<>();
                dateAvailability.put(date, isAvailable);
                scheduleList.add(new Schedule(doctorId, dateAvailability));
            }
        } catch (IOException e) {
            System.err.println("Error loading schedule data: " + e.getMessage());
        }
        return scheduleList;
    }

    private void writeAll(List<Schedule> scheduleList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {  // overwrite mode
            writer.write("doctorId,date,isAvailable");
            writer.newLine();
            for (Schedule schedule : scheduleList) {
                for (HashMap.Entry<LocalDate, Boolean> dateAvailability : schedule.getDateAvailability().entrySet()) {
                    writer.write(formatAvailabilityCSVLine(schedule.getDoctorId(),dateAvailability));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving availability data: " + e.getMessage());
        }
    }

    private String formatAvailabilityCSVLine(String doctorId, HashMap.Entry<LocalDate, Boolean> dateAvailability) {
        return String.join(",",
                doctorId,
                dateAvailability.getKey().format(DATE_FORMAT),
                String.valueOf(dateAvailability.getValue())
        );
    }

    public void add(Schedule schedule) {
        List<Schedule> allSchedules = fetch();
        allSchedules.removeIf(existing -> existing.getDoctorId().equals(schedule.getDoctorId()));
        allSchedules.add(schedule);
        writeAll(allSchedules);
    }

    public void deleteDate(String doctorId, LocalDate date) {
        List<Schedule> allSchedules = fetch();
        for (Schedule schedule : allSchedules) {
            if (schedule.getDoctorId().equals(doctorId)) {
                schedule.getDateAvailability().remove(date);
                break;
            }
        }
        writeAll(allSchedules);
    }

    public void updateIsAvailable(String doctorId, LocalDate date, Boolean isAvailable) {
        List<Schedule> allSchedules = fetch();
        for (Schedule schedule : allSchedules) {
            if (schedule.getDoctorId().equals(doctorId)) {
                schedule.getDateAvailability().put(date, isAvailable);
                break;
            }
        }
        writeAll(allSchedules);
    }

    public Schedule find(String doctorId) {
        List<Schedule> scheduleList = fetch();
        for (Schedule schedule : scheduleList) {
            if (schedule.getDoctorId().equals(doctorId)) {return schedule;}
        }
        return null;
    }

}

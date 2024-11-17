package Data.DataAccess;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Shared.Schedule;

/**
 * The ScheduleDAO class handles the data access for doctor schedules.
 * This includes loading, saving, adding, deleting, and updating schedules from a CSV file.
 */
public class ScheduleDAO {

    private static final String FILE_PATH = "src/Data/Assets/Schedule.csv";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Fetches all schedules from the CSV file.
     * 
     * @return A list of schedules.
     */
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
                if (values.length != 4) { // Ensure exactly 4 fields
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }
                String doctorId = values[0];
                LocalDate date = LocalDate.parse(values[1], DATE_FORMAT);
                Boolean isAvailable = Boolean.parseBoolean(values[2]);
                String time = values[3];
                Schedule existingSchedule = null;
                for (Schedule s : scheduleList) {
                    if (s.getDoctorId().equals(doctorId)) {
                        existingSchedule = s;
                        break;
                    }
                }

                if (existingSchedule == null) {
                    // Create a new schedule if none exists for this doctor
                    HashMap<LocalDate, Boolean> dateAvailability = new HashMap<>();
                    HashMap<LocalDate, String> dateTime = new HashMap<>();
                    dateAvailability.put(date, isAvailable);
                    dateTime.put(date, time);
                    scheduleList.add(new Schedule(doctorId, dateAvailability, dateTime));
                } else {
                    // Add date availability to the existing schedule
                    existingSchedule.getDateAvailability().put(date, isAvailable);
                    existingSchedule.getDateTime().put(date, time);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading schedule data: " + e.getMessage());
        }
        return scheduleList;
    }

    /**
     * Writes all schedules to the CSV file.
     * 
     * @param scheduleList A list of schedules to write to the CSV file.
     */
    private void writeAll(List<Schedule> scheduleList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {  // overwrite mode
            writer.write("doctorId,date,isAvailable,time");
            writer.newLine();
            for (Schedule schedule : scheduleList) {
                for (LocalDate date : schedule.getDateAvailability().keySet()) {
                    Boolean isAvailable = schedule.getDateAvailability().get(date);
                    String time = schedule.getDateTime().get(date);
                    writer.write(formatAvailabilityCSVLine(schedule.getDoctorId(), date, isAvailable, time));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving availability data: " + e.getMessage());
        }
    }

    /**
     * Formats the availability details of a doctor as a CSV line.
     * 
     * @param doctorId The doctor's ID.
     * @param date The date of availability.
     * @param isAvailable The availability status.
     * @param time The time associated with the availability.
     * @return A formatted CSV line for the schedule.
     */
    private String formatAvailabilityCSVLine(String doctorId, LocalDate date, Boolean isAvailable, String time) {
        return String.join(",",
                doctorId,
                date.format(DATE_FORMAT),
                String.valueOf(isAvailable),
                time
        );
    }

    /**
     * Adds a new schedule or updates an existing schedule for a doctor.
     * 
     * @param schedule The schedule to be added or updated.
     */
    public void add(Schedule schedule) {
        List<Schedule> allSchedules = fetch();
        allSchedules.removeIf(existing -> existing.getDoctorId().equals(schedule.getDoctorId()));
        allSchedules.add(schedule);
        System.out.println("New Doctor Entry Recorded!");
        writeAll(allSchedules);
    }

    /**
     * Deletes a specific date from a doctor's schedule.
     * 
     * @param doctorId The doctor's ID.
     * @param date The date to be deleted from the schedule.
     */
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

    /**
     * Updates the availability status and time for a specific date in a doctor's schedule.
     * 
     * @param doctorId The doctor's ID.
     * @param date The date to be updated.
     * @param isAvailable The updated availability status.
     * @param time The updated time.
     */
    public void updateIsAvailable(String doctorId, LocalDate date, Boolean isAvailable, String time) {
        List<Schedule> allSchedules = fetch();
        for (Schedule schedule : allSchedules) {
            if (schedule.getDoctorId().equals(doctorId)) {
                schedule.getDateAvailability().put(date, isAvailable);
                schedule.getDateTime().put(date, time);
                System.out.println("Availability Updated Successfully!");
                break;
            }
        }
        writeAll(allSchedules);
    }

    /**
     * Finds a schedule for a specific doctor by their ID.
     * 
     * @param doctorId The doctor's ID.
     * @return The schedule of the doctor if found, or null if not found.
     */
    public Schedule find(String doctorId) {
        List<Schedule> scheduleList = fetch();
        for (Schedule schedule : scheduleList) {
            if (schedule.getDoctorId().equals(doctorId)) {
                return schedule;
            }
        }
        return null;
    }

}

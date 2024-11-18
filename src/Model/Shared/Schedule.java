package Model.Shared;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Represents a schedule for a doctor, including the availability on specific dates and the time for appointments.
 * <p>
 * The Schedule class is used to manage doctors' availability and appointments across multiple dates.
 */
public class Schedule {
    private String doctorId;
    private HashMap<LocalDate, Boolean> dateAvailability; // Maps a date to a Boolean representing availability
    private HashMap<LocalDate, String> dateTime; // Maps a date to the available time for that day

    /**
     * Constructs a Schedule for a specific doctor with empty availability.
     *
     * @param doctorId The unique ID of the doctor whose schedule is being created.
     */
    public Schedule(String doctorId) {
        this.doctorId = doctorId;
        this.dateAvailability = new HashMap<>();
        this.dateTime = new HashMap<>();
    }

    /**
     * Constructs a Schedule for a specific doctor with the given availability and appointment times.
     *
     * @param doctorId        The unique ID of the doctor whose schedule is being created.
     * @param dateAvailability A mapping of dates to their availability status (true for available, false for unavailable).
     * @param dateTime        A mapping of dates to the available times for that day.
     */
    public Schedule(String doctorId, HashMap<LocalDate, Boolean> dateAvailability, HashMap<LocalDate, String> dateTime) {
        this.doctorId = doctorId;
        this.dateAvailability = dateAvailability;
        this.dateTime = dateTime;
    }

    // Getters

    /**
     * Gets the doctor ID associated with this schedule.
     *
     * @return The unique ID of the doctor.
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Gets the availability for each date in the schedule.
     * <p>
     * The availability is represented by a mapping of LocalDate to a Boolean value.
     * A value of true means the doctor is available on that date.
     *
     * @return A HashMap of dates to their availability status.
     */
    public HashMap<LocalDate, Boolean> getDateAvailability() {
        return dateAvailability;
    }

    /**
     * Gets the available times for each date in the schedule.
     * <p>
     * The available times are represented by a mapping of LocalDate to a time string.
     *
     * @return A HashMap of dates to their available times.
     */
    public HashMap<LocalDate, String> getDateTime() {
        return dateTime;
    }

    // Setters

    /**
     * Sets the doctor ID for this schedule.
     *
     * @param doctorId The unique ID of the doctor.
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Sets the availability for specific dates in the schedule.
     *
     * @param dateAvailability A HashMap of dates to their availability status.
     */
    public void setDateAvailability(HashMap<LocalDate, Boolean> dateAvailability) {
        this.dateAvailability = dateAvailability;
    }

    /**
     * Sets the available times for specific dates in the schedule.
     *
     * @param dateTime A HashMap of dates to their available times.
     */
    public void setDateTime(HashMap<LocalDate, String> dateTime) {
        this.dateTime = dateTime;
    }
}

package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The CustomDate class encapsulates a date and time using LocalDate and LocalTime.
 * It provides methods to format the date and time as a string and to compare two CustomDate
 *  objects.
 *  @param localDate The LocalDate component of the CustomDate.
 *  @param localTime The LocalTime component of the CustomDate.
 */
public class CustomDate {
    private LocalDate localDate;
    private LocalTime localTime;

    /**
     * Constructor for CustomDate class.
     * @param localDate
     * @param localTime
     */
    public CustomDate(LocalDate localDate, LocalTime localTime) {
        this.localDate = localDate;
        this.localTime = localTime;
    }
    public LocalDate getLocalDate() {
        return localDate;
    }
    public LocalTime getLocalTime() {
        return localTime;
    }

    /**
     * Method to get the date and time in the format "dd/MM/yyyy HHmm".
     * @return Formatted date and time string.
     */
    public String getLocalDateAndTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String result = localDate.format(dateFormatter) + " " + localTime.format(timeFormatter);
        return result;
    }

    /**
     * Method to compare if this CustomDate is after another CustomDate.
     * @param toDate
     * @return true if this CustomDate is after the toDate, false otherwise.
     */
    public boolean isAfter(CustomDate toDate) {
        if (this.localDate.isAfter(toDate.getLocalDate())) {
            return true;
        } else if (this.localDate.isEqual(toDate.getLocalDate())) {
            return this.localTime.isAfter(toDate.getLocalTime());
        } else {
            return false;
        }
    }
}

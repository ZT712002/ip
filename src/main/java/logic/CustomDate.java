package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CustomDate {
    private LocalDate localDate;
    private LocalTime localTime;
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


    public String getLocalDateAndTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String result = localDate.format(dateFormatter) + " " + localTime.format(timeFormatter);
        return result;
    }

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

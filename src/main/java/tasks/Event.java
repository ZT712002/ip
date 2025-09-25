package tasks;

import logic.CustomDate;

public class Event extends Task {
    private CustomDate from;
    private CustomDate to;

    public Event(String description, CustomDate from, CustomDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return from.getLocalDateAndTime().toString();
    }
    public String getTo() {
        return to.getLocalDateAndTime().toString();
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}


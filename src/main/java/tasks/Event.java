package tasks;

import logic.CustomDate;

/**
 * Represents an event task with a description, start date/time, and end date/time.
 * Inherits from the Task class.
 * Overrides the toString method to include the task type and date/time information.
 * The task type is represented by "[E]".
 * Example: [E][ ] project meeting (from: 2023-10-01
 * 10:00 to: 2023-10-01 12:00)
 * where "[E]" indicates it's an Event task and "[ ]" indicates it's not done.
 * If the task is done, it will be represented as "[E][X] project meeting
 * (from: 2023-10-01 10:00 to: 202
 * 3-10-01 12:00)".
 * @param description The description of the Event task.
 * @param from The start date/time of the event.
 * @param to The end date/time of the event.
 */
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


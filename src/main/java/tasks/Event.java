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
    /**
     * Constructor for Event class.
     * @param description The description of the event task.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, CustomDate from, CustomDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Method to get the start date/time of the event.
     * @return Formatted start date and time string.
     */
    public String getFrom() {
        return from.getLocalDateAndTime().toString();
    }

    /**
     * Returns the end date/time of the event.
     * @return
     */
    public String getTo() {
        return to.getLocalDateAndTime().toString();
    }

    /**
     * Returns the string representation of the Event task.
     * Overrides the toString method from the Task class.
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}


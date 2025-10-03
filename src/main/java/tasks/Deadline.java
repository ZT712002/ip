package tasks;

import logic.CustomDate;

/**
 * Represents a deadline task with a description and a due date/time.
 * Inherits from the Task class.
 * Overrides the toString method to include the task type and due date/time information.
 * The task type is represented by "[D]".
 * Example: [D][ ] submit report (by: 2023-10-01
 * 23:59)
 * where "[D]" indicates it's a Deadline task and "[ ]" indicates it's not done.
 * If the task is done, it will be represented as "[D][X] submit report
 * (by: 2023-10-01 23:59)".
 * @param description The description of the deadline task.
 * @param by The due date/time of the deadline task.
 */
public class Deadline extends Task {

    private CustomDate by;
    /** * Constructor for Deadline class.
     * @param description The description of the deadline task.
     * @param by The due date/time of the deadline task.
     */
    public Deadline(String description, CustomDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Method to get the due date/time of the deadline task.
     * @return
     */
    public String getBy() {
        return this.by.getLocalDateAndTime();
    }
    /**
     * Returns the string representation of the Deadline task.
     * Overrides the toString method from the Task class.
     * @return A string representing the Deadline task, including its type, status, and due date/time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}

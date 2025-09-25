package tasks;

import logic.CustomDate;

public class Deadline extends Task {

    private CustomDate by;

    public Deadline(String description, CustomDate by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by.getLocalDateAndTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}

package tasks;

/**
 * Represents a general Task with a description and completion status.
 * This is an abstract class that can be extended to create specific types of tasks.
 * It provides methods to mark the task as done or not done, and to get its string representation.
 * @param description The description of the task.
 * @param isDone The completion status of the task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getDescription() {
        return this.description;
    }


    public void markAsDone() {
        this.isDone = true;
    }


    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
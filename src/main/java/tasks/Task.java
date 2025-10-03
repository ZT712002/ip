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

    /**
     * Returns the description of the task.
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Marks the task as done by setting the isDone attribute to true.
     */

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done by setting the isDone attribute to false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }
    /**
     * Returns the status icon of the task.
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the string representation of the task.
     * This includes the status icon and the task description.
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}

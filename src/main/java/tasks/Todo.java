package tasks;

/**
 * Represents a Todo task.
 * @param description The description of the Todo task.
 *                    Inherits from the Task class.
 *                    Overrides the toString method to include the task type.
 *                    The task type is represented by "[T]".
 *                    Example: [T][ ] read book
 *                    where "[T]" indicates it's a Todo task and "[ ]" indicates it's not done.
 *                    If the task is done, it will be represented as "[T][X] read book".
 *
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns the string representation of the Todo task.
     * Overrides the toString method from the Task class.
     * @return A string representing the Todo task, including its type and status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("You have added \"" + description + "\" to your task list.");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            // The toString() method of the Tgit git ask class is called automatically.
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markTask(int taskNumber) {
        if (isValidIndex(taskNumber)) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void unmarkTask(int taskNumber) {
        if (isValidIndex(taskNumber)) {
            Task task = tasks.get(taskNumber - 1);
            task.unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private boolean isValidIndex(int taskNumber) {
        return taskNumber > 0 && taskNumber <= tasks.size();
    }
}
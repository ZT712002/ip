package logic;

import exception.IllegalTaskNumberException;
import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markTask(int taskNumber) throws IllegalTaskNumberException {
        try {
            if (isValidIndex(taskNumber)) {
                Task task = tasks.get(taskNumber - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (IllegalTaskNumberException e) {
            throw new IllegalTaskNumberException();
        }
    }

    public void unmarkTask(int taskNumber) throws IllegalTaskNumberException {
        try {
            if (isValidIndex(taskNumber)) {
                Task task = tasks.get(taskNumber - 1);
                task.unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + task);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (IllegalTaskNumberException e) {
            throw new IllegalTaskNumberException();
        }
    }

    private boolean isValidIndex(int taskNumber) throws IllegalTaskNumberException {
        return taskNumber > 0 && taskNumber <= tasks.size();
    }
}
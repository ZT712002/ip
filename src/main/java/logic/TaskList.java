package logic;

import exception.IllegalTaskNumberException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

/**
 * Manages a list of tasks, allowing addition, deletion, marking, unmarking, and listing of tasks.
 * Provides methods to manipulate and retrieve tasks from the list.
 * Supports different types of tasks such as Todo, Deadline, and Event.
 * @param tasks The list of tasks being managed.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    /**
     * Constructor for TaskList class.
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Adds a new task to the task list and prints a confirmation message.
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    /**
     * Adds a new task to the task list without printing a confirmation message.
     * This method is used when loading tasks from storage.
     * @param task The task to be added to the list.
     * @param isLoading A boolean flag indicating that the task is being loaded from storage.
     */
    public void addTask(Task task, boolean isLoading){
        tasks.add(task);
    }
    /**
     * Lists all tasks in the task list with their respective indices.
     * If the list is empty, it prints a message indicating that there are no tasks.
     */
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

    /**
     * Marks the task at the given task number as done.
     * @param taskNumber
     * @throws IllegalTaskNumberException
     */
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

    /**
     * Unmarks the task at the given task number as not done.
     * @param taskNumber
     * @throws IllegalTaskNumberException
     */
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

    /**
     * Checks if the given task number is a valid index in the task list.
     * @param taskNumber
     * @return
     * @throws IllegalTaskNumberException
     */
    private boolean isValidIndex(int taskNumber) throws IllegalTaskNumberException {
        return taskNumber > 0 && taskNumber <= tasks.size();
    }
    /**
     * Returns the size of the task list.
     * @return
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index in a specific string format for storage.
     * @param i
     * @return
     */
    public String getTask(int i) {
        String result ="";
        if(tasks.get(i) instanceof Todo) {
            Todo temp = (Todo) tasks.get(i);
            result = "T , " + (temp.getStatusIcon().equals("[X]") ? "1" : "0") + " , " + temp.getDescription() + "\n";
        }
        else if (tasks.get(i) instanceof Deadline){
            Deadline temp = (Deadline) tasks.get(i);
            result = "D , " + (temp.getStatusIcon().equals("[X]") ? "1" : "0") + " , " + temp.getDescription() + " , "
                    + ((Deadline) temp).getBy()+ "\n";
        }
        else if (tasks.get(i) instanceof Event){
            Event temp = (Event) tasks.get(i);
            result = "E , " + (temp.getStatusIcon().equals("[X]") ? "1" : "0") + " , " + temp.getDescription() + " , "
                    + ((Event) temp).getFrom() + " , " + ((Event) temp).getTo()+ "\n";
        }
        return result;
    }

    /**
     * Deletes the task at the given task number from the task list.
     * @param taskNumber
     * @throws IllegalTaskNumberException
     */

    public void deleteTask(int taskNumber) throws IllegalTaskNumberException {
        try {
            if (isValidIndex(taskNumber)) {
                Task task = tasks.remove(taskNumber - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (IllegalTaskNumberException e) {
            throw new IllegalTaskNumberException();
        }
    }
    /**
     * Checks if the task list is empty.
     * @return
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    /**
     * Returns the entire list of tasks.
     * @return
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
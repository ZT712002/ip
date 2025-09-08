package command;

import logic.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.util.Scanner;

public class Passer {
    private final Scanner IN;
    private boolean isPasserActive;
    private String userInput;


    public String getUserInput() {
        return userInput;
    }

    public Passer() {
        this.IN = new Scanner(System.in);
        this.isPasserActive = true;
    }

    public void setUserInput() {
        this.userInput = IN.nextLine();
    }

    public boolean isPasserActive() {
        return isPasserActive;
    }

    public void setIsPasserActiveOff() {
        isPasserActive = false;
    }

    public void processCommand(String userInput, TaskList tasks) {
        String[] commandParts = userInput.split(" ", 2);
        String command = commandParts[0].toLowerCase();
        String arguments = commandParts.length > 1 ? commandParts[1] : "";
        try {
            switch (command) {
            case "list":
                tasks.listTasks();
                break;
            case "mark":
                handleMarkUnmark(tasks, arguments, true);
                break;
            case "unmark":
                handleMarkUnmark(tasks, arguments, false);
                break;
            case "todo":
                handleTodo(tasks, arguments);
                break;
            case "deadline":
                handleDeadline(tasks, arguments);
                break;
            case "event":
                handleEvent(tasks, arguments);
                break;
            case "bye":
                setIsPasserActiveOff();
                break;
            default:
                System.out.println("I'm sorry, but I don't know what that means.");

            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    private void handleMarkUnmark(TaskList tasks, String arguments, boolean isMark) {
        try {
            int taskNumber = Integer.parseInt(arguments.trim());
            if (isMark) {
                tasks.markTask(taskNumber);
            } else {
                tasks.unmarkTask(taskNumber);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        }
    }

    private void handleTodo(TaskList tasks, String arguments) {
        if (arguments.isEmpty()) {
            System.out.println("The description of a todo cannot be empty.");
            return;
        }
        tasks.addTask(new Todo(arguments));
    }

    private void handleEvent(TaskList tasks, String arguments) {
        String[] parts = arguments.split(" /from");
        if (parts.length < 2) {
            System.out.println("Please provide both description and start time for the event in this format " +
                    "event <description> /from <start> /to <end>.");
            return;
        }
        String[] timeParts = parts[1].split(" /to");
        if (timeParts.length < 2) {
            System.out.println("Please provide both description and start time for the event in this format " +
                    "event <description> /from <start> /to <end>.");
            return;
        }
        tasks.addTask(new Event(parts[0], timeParts[0], timeParts[1]));
    }

    private void handleDeadline(TaskList tasks, String arguments) {
        String[] parts = arguments.split(" /by");
        if (parts.length < 2) {
            System.out.println("Please provide both description and due date for the deadline in this format " +
                    "deadline <description> /by <due date>.");
            return;
        }
        tasks.addTask(new Deadline(parts[0], parts[1]));
    }
}

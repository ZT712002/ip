package logic;

import exception.IllegalTaskNumberException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class Command {
    private String commandType;
    private String arguments;

    public Boolean getExit() {
        return isExit;
    }

    public void setExit(Boolean exit) {
        isExit = exit;
    }

    private Boolean isExit = false;

    public Command(String commandType, String arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public void processCommand(TaskList tasks) {
        String commandType = getCommandType();
        String arguments = getArguments();
        try {
            switch (commandType) {
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
                setExit(true);
                break;
            case "delete":
                handleDelete(tasks, arguments);
                break;
            default:
                System.out.println("I'm sorry, but I don't know what that means.");

            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    private String getCommandType() {
        return commandType;
    }
    private String getArguments() {
        return arguments;
    }


    private static void handleDelete(TaskList tasks, String arguments) {
        try {
            int taskNumber = Integer.parseInt(arguments.trim());
            tasks.deleteTask(taskNumber);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (IllegalTaskNumberException e) {
            throw new RuntimeException(e);
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
        } catch (IllegalTaskNumberException e) {
            throw new RuntimeException(e);
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

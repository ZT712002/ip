package logic;

import exception.IllegalTaskNumberException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.sql.SQLOutput;
import java.util.ArrayList;

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
                case "find":
                    handleFind(tasks, arguments);
                    break;
            default:
                System.out.println("I'm sorry, but I don't know what that means.");

            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    private void handleFind(TaskList tasks, String arguments) {
        if (arguments.isEmpty()) {
            System.out.println("The description of a find cannot be empty.");
            return;
        }
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task t : tasks.getTaskList()) {
            if (t.getDescription().contains(arguments)) {
                searchResults.add(t);
            }
        }
        if (searchResults.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are your ");
            for (Task t : searchResults) {
                System.out.println(t.toString());
            }
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
        CustomDate fromDate = Parser.parseDate(timeParts[0].trim());
        CustomDate toDate = Parser.parseDate(timeParts[1].trim());
        if (fromDate.isAfter(toDate)) {
            System.out.println("The start time cannot be after the end time.");
            return;
        }
        tasks.addTask(new Event(parts[0], fromDate, toDate));
    }

    private void handleDeadline(TaskList tasks, String arguments) {
        String[] parts = arguments.split(" /by");
        System.out.println(parts[1]);
        if (parts.length < 2) {
            System.out.println("Please provide both description and due date for the deadline in this format " +
                    "deadline <description> /by <due date> in dd/mm/yyyy time.");
            return;
        }
        CustomDate date = Parser.parseDate(parts[1].trim());
        tasks.addTask(new Deadline(parts[0], date));
    }

}

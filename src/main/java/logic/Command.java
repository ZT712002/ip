package logic;

import exception.IllegalTaskNumberException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Handles and processes user commands for managing tasks.
 * Supports commands such as adding tasks, marking/unmarking tasks,
 * deleting tasks, listing tasks, and finding tasks.
 * Each command is processed based on its type and associated arguments.
 * Provides feedback to the user for each operation performed.
 * @param commandType The type of command (e.g., "todo", "deadline",
 *                    "event", "mark", "unmark", "delete", "list", "find", "bye").
 * @param
 */
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

    /**
     * Processes the command based on its type and arguments.
     * Executes the corresponding action on the provided TaskList.
     * Handles various command types such as adding tasks, marking/unmarking tasks,
     * deleting tasks, listing tasks, and exiting the program.
     * @param tasks
     * @param commandType The type of command to be processed.
     * @param arguments The arguments associated with the command.
     */
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

    /**
     * Handles the 'find' command to search for tasks containing the specified keyword.
     * If the arguments are empty, it prompts the user to provide a valid keyword.
     * If the task list is empty, it informs the user that there are no tasks to search.
     * It then searches through the task list and collects tasks that match the keyword.
     * Finally, it displays the matching tasks or informs the user if no matches were found.
     * @param tasks
     * @param arguments
     */
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

    /**
     * Handles the 'delete' command to remove a task from the task list.
     * It parses the task number from the arguments and attempts to delete the corresponding task.
     * If the task number is invalid or not a number, it prompts the user with an appropriate message.
     * If the task number is valid, it deletes the task and provides feedback to the user.
     * @param tasks
     * @param arguments
     */
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

    /**
     * Handles the 'mark' and 'unmark' commands to update the completion status of a task.
     * It parses the task number from the arguments and attempts to mark or unmark the corresponding task.
     * If the task number is invalid or not a number, it prompts the user with an appropriate message.
     * If the task number is valid, it updates the task's status and provides feedback to the user.
     * @param tasks
     * @param arguments
     * @param isMark
     */
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

    /**
     * Handles the 'todo' command to add a new Todo task to the task list.
     * If the arguments are empty, it prompts the user to provide a valid description.
     * If a valid description is provided, it creates a new Todo task and adds it to the task list.
     * Finally, it provides feedback to the user about the added task.
     * @param tasks
     * @param arguments
     */
    private void handleTodo(TaskList tasks, String arguments) {
        if (arguments.isEmpty()) {
            System.out.println("The description of a todo cannot be empty.");
            return;
        }
        tasks.addTask(new Todo(arguments));
    }

    /**
     * Handles the 'event' command to add a new Event task to the task list.
     * It parses the description, start time, and end time from the arguments.
     * If the arguments are not in the correct format or if the start time is after the end time,
     * it prompts the user with an appropriate message.
     * If the arguments are valid, it creates a new Event task and adds it to the task list.
     * Finally, it provides feedback to the user about the added task.
     * @param tasks
     * @param arguments
     */
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
        if(fromDate == null || toDate == null) {
            System.out.println("Please provide the date and time in the format dd/mm/yyyy HHmm");
            return;
        }
        tasks.addTask(new Event(parts[0], fromDate, toDate));
    }

    /**
     * Handles the 'deadline' command to add a new Deadline task to the task list.
     * It parses the description and due date from the arguments.
     * If the arguments are not in the correct format, it prompts the user with an appropriate message.
     * If the arguments are valid, it creates a new Deadline task and adds it to the task list.
     * Finally, it provides feedback to the user about the added task.
     * @param tasks
     * @param arguments
     */
    private void handleDeadline(TaskList tasks, String arguments) {
        String[] parts = arguments.split(" /by");
        System.out.println(parts[1]);
        if (parts.length < 2) {
            System.out.println("Please provide both description and due date for the deadline in this format " +
                    "deadline <description> /by <due date> in dd/mm/yyyy time.");
            return;
        }
        CustomDate date = Parser.parseDate(parts[1].trim());
        if(date == null) {
            System.out.println("Please provide the date and time in the format dd/mm/yyyy HHmm");
            return;
        }
        tasks.addTask(new Deadline(parts[0], date));
    }

}

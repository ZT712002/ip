package ui;

import exception.IllegalTaskNumberException;
import logic.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ui {
    private final Scanner IN;
    private boolean isPasserActive;
    private String userInput;
    public static final String FILE_PATH = "./data/";
    public static final String FILE_NAME = "Tasks.txt";


    public String getUserInput() {
        return userInput;
    }

    public Ui() {
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
                saveData(tasks);
                setIsPasserActiveOff();
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

    private void saveData(TaskList tasks) throws IOException {
        System.out.println("Saving Data Now...");
        String fileName = FILE_PATH + FILE_NAME;
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        try {
            for (int i = 0; i < tasks.getSize(); i++) {
                String taskString = tasks.getTask(i);
                bw.write(taskString);
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            bw.close();
        }
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

    public void initializeList(TaskList tasks) throws IOException {
        checkIfPathExists();
        try {
            File file = new File(FILE_PATH + FILE_NAME);
            if(!file.exists()) {
                System.out.println("File does not exist. Creating new file...");
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Loading Data Now...");
        loadData(tasks);
        tasks.listTasks();



    }

    private void loadData(TaskList tasks) throws IOException {
        String fileName = FILE_PATH + FILE_NAME;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                String[] args = line.split(",");
                String taskType = args[0].trim();
                boolean isDone = args[1].trim().equals("1");
                String description = args[2].trim();
                switch (taskType) {
                case "T":
                    Todo todo = new Todo(description);
                    if (isDone) {
                        todo.markAsDone();
                    }
                    tasks.addTask(todo, false);
                    break;
                case "D":
                    String by = args[3].trim();
                    Deadline deadline = new Deadline(description, by);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    tasks.addTask(deadline, false);
                    break;
                case "E":
                    String from = args[3].trim();
                    String to = args[4].trim();
                    Event event = new Event(description, from, to);
                    if (isDone) {
                        event.markAsDone();
                    }
                    tasks.addTask(event,false);
                    break;
                default:
                    System.out.println("Unknown task type or file corrupted please check your file. " + taskType);
                    break;
                }
                line = br.readLine();

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }


    }

    private void checkIfPathExists() {
        Path dirPath = Paths.get(FILE_PATH);
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
                System.out.println("Directory created: " + dirPath.toAbsolutePath());
            } catch (Exception e) {
                System.out.println("Failed to create directory: " + e.getMessage());
            }
        }

    }
}

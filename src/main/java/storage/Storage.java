package storage;
import logic.CustomDate;
import logic.Parser;
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

/**
 * The Storage class is responsible for handling the loading and saving of tasks to a file.
 * It provides methods to initialize the task list from a file and save the current task list to a file.
 * The class ensures that the necessary directories and files exist, creating them if they do not.
 * It supports loading tasks of different types (Todo, Deadline, Event) from a specified file format.
 * @param FILE_PATH The directory path where the tasks file is stored.
 *                  Default is "./data/".
 * @param FILE_NAME The name of the file to target.
 *                  Default is "Tasks.txt".
 */
public class Storage {
    public static final String FILE_PATH = "./data/";
    public static final String FILE_NAME = "Tasks.txt";

    /**
     * Initializes the task list by loading tasks from the specified file.
     * If the file or directory does not exist, they are created.
     * After loading, it lists the tasks in the provided TaskList.
     * @param tasks
     * @throws IOException
     */
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

    /**
     * Loads tasks from the specified file into the provided TaskList.
     * It reads each line of the file, parses the task information, and creates the corresponding
     * task objects (Todo, Deadline, Event) which are then added to the TaskList.
     * The method handles different task types and their attributes based on the file format.
     * @param tasks
     */
    private void loadData(TaskList tasks) {
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
                    CustomDate date = Parser.parseDate(by);
                    Deadline deadline = new Deadline(description, date);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    tasks.addTask(deadline, false);
                    break;
                case "E":
                    String from = args[3].trim();
                    String to = args[4].trim();
                    CustomDate dateFrom = Parser.parseDate(from);
                    CustomDate dateTo = Parser.parseDate(to);
                    Event event = new Event(description, dateFrom, dateTo);
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
    /**
     * Checks if the specified directory path exists.
     * If it does not exist, the method creates the necessary directories.
     * This ensures that the file operations can be performed without errors related to missing directories.
     */
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

    /**
     * Saves the current tasks in the provided TaskList to the specified file.
     * It writes each task's information in a specific format to the file, ensuring that
     * the tasks can be reloaded correctly in the future.
     * The method handles any IOExceptions that may occur during the file writing process.
     * Only invoked when "bye" command is issued. Else the data will be corrupted
     * @param tasks
     * @throws IOException
     */
    public void saveData(TaskList tasks) throws IOException {
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
}

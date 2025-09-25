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
public class Storage {
    public static final String FILE_PATH = "./data/";
    public static final String FILE_NAME = "Tasks.txt";

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

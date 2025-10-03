import logic.Command;
import logic.Parser;
import storage.Storage;
import ui.Ui;
import logic.TaskList;

import java.io.IOException;

/**
 * Joebot666 is a personal assistant chatbot that helps users manage their tasks.
 * It supports adding, marking, unmarking, deleting, and finding tasks, as well
 * as saving and loading tasks from a file.
 * The chatbot interacts with users through a command-line interface.
 * It uses classes such as TaskList, Command, Parser, Storage, and Ui to handle
 * various functionalities.
 * The main method initializes the chatbot and starts the interaction loop.
 * It continues to run until the user issues the "bye" command, at which point it saves
 * the current tasks to a file and exits.
 * @Author ZT712002
 * @Version 1.0
 *
 * Example Commands
 *  -todo read book
 *  -deadline submit assignment /by 12/09/2023 2359
 *  -list
 */

public class Joebot666 {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    public Joebot666() throws IOException {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage();
        greetUser();
    }
    /**
     * Runs the main interaction loop of the chatbot.
     * It continuously prompts the user for input, processes commands,
     * and updates the task list until the user decides to exit.
     */

    public void run() {
        while (ui.isPasserActive()) {
            ui.setUserInput();
            String unprocessedInput = ui.getUserInput();
            Command c = Parser.parseCommand(unprocessedInput);
            c.processCommand(tasks);
            boolean isExitCommand = c.getExit();
            if (isExitCommand) {
                ui.setIsPasserActiveOff();
            }
            ui.printLineDivider();
        }
        saveDataAndExit();
    }

    /**
     * Saves the current tasks to a file and exits the program.
     * If an error occurs during saving, it prints an error message.
     */
    private void saveDataAndExit() {
        try {
            storage.saveData(tasks);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        ui.printGoodbyeMessage();
    }

    /**
     * Greets the user and initializes the task list by loading data from a file.
     * If an error occurs during initialization, it throws an IOException.
     * @throws IOException if there is an error loading the data
     */
    private void greetUser() throws IOException {
        ui.printWelcomeMessage();
        storage.initializeList(tasks);
        ui.printLineDivider();
    }
    /** main method to start the chatbot application.
     * It creates an instance of Joebot666 and calls the run method to start the interaction loop.
     * @param args command-line arguments (not used)
     * @throws IOException if there is an error during initialization
     */
    public static void main(String[] args) throws IOException {
        new Joebot666().run();
    }

}

import logic.Command;
import logic.Parser;
import storage.Storage;
import ui.Ui;
import logic.TaskList;

import java.io.IOException;


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

    public void run() {
        while (ui.isPasserActive()) {
            ui.printWelcomeMessage();
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

    private void saveDataAndExit() {
        try {
            storage.saveData(tasks);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        ui.printGoodbyeMessage();
    }

    private void greetUser() throws IOException {
        ui.printWelcomeMessage();
        storage.initializeList(tasks);
    }

    public static void main(String[] args) throws IOException {
        new Joebot666().run();
    }

}
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

    public void run() throws IOException {
        while (ui.isPasserActive()) {
            System.out.println("*************************************");
            ui.setUserInput();
            ui.processCommand(ui.getUserInput(), tasks);
        }
        saveDataAndExit();
    }

    private void saveDataAndExit() {
        try {
            storage.saveData(tasks);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        System.out.println("Bye. See you next time!");
        System.out.println("*************************************");
    }

    private void greetUser() throws IOException {
        System.out.println("Hello I'm JoeBot666");
        System.out.println("What can I do for you today?");
        storage.initializeList(tasks);
    }

    public static void main(String[] args) throws IOException {
        new Joebot666().run();
    }

}
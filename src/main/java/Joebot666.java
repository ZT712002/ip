import ui.Ui;
import logic.TaskList;

import java.io.IOException;


public class Joebot666 {
    private TaskList tasks;
    private Ui ui;
    public Joebot666() throws IOException {
        tasks = new TaskList();
        ui = new Ui();
        greetUser();
    }

    private void greetUser() throws IOException {
        System.out.println("Hello I'm JoeBot666");
        System.out.println("What can I do for you today?");
        ui.initializeList(tasks);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello I'm JoeBot666");
        System.out.println("What can I do for you today?");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        ui.initializeList(tasks);
        while (ui.isPasserActive()) {
            System.out.println("*************************************");
            ui.setUserInput();
            ui.processCommand(ui.getUserInput(), tasks);
        }
        System.out.println("Bye. See you next time!");
        System.out.println("*************************************");
    }

}
import command.Passer;
import logic.TaskList;

public class Joebot666 {

    public static void main(String[] args) {
        System.out.println("Hello I'm JoeBot666");
        System.out.println("What can I do for you today?");

        TaskList tasks = new TaskList();
        Passer passer = new Passer();
        while (passer.isPasserActive()) {
            System.out.println("*************************************");
            passer.setUserInput();
            passer.processCommand(passer.getUserInput(), tasks);
        }
        System.out.println("Bye. See you next time!");
        System.out.println("*************************************");
    }
}
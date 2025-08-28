import java.util.Scanner;

public class Joebot666 {

    public static void main(String[] args) {
        System.out.println("Hello I'm JoeBot666");
        System.out.println("What can I do for you today?");

        TaskList taskList = new TaskList();
        Scanner in = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("*************************************");
            userInput = in.nextLine();
            String[] commandParts = userInput.split(" ", 2);
            String command = commandParts[0].toLowerCase();

            switch (command) {
            case "list":
                taskList.listTasks();
                break;
            case "mark":
                try {
                    int itemNumber = Integer.parseInt(commandParts[1]);
                    taskList.markTask(itemNumber);
                } catch (Exception e) {
                    System.out.println("Please provide a valid item number to mark.");
                }
                break;
            case "unmark":
                try {
                    int itemNumber = Integer.parseInt(commandParts[1]);
                    taskList.unmarkTask(itemNumber);
                } catch (Exception e) {
                    System.out.println("Please provide a valid item number to unmark.");
                }
                break;
            case "bye":
                break;
            default:
                taskList.addTask(userInput);
                break;
            }
        } while (!userInput.equalsIgnoreCase("bye"));

        System.out.println("Bye. See you next time!");
        System.out.println("*************************************");
    }
}
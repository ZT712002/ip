import java.util.Scanner;

public class Joebot666 {
    public static void main(String[] args) {
        System.out.println("Hello I'm JoeBot666");
        System.out.println("What can I do for you today?");
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println("*************************************");
            System.out.println("Why do you say '" + userInput + "'?");
            System.out.println("*************************************");
            userInput = in.nextLine();
        }
        System.out.println("Bye. See you next time!");
    }
}

package ui;


import java.util.Scanner;
/**
 * Ui class handles user interactions, including input and output.
 * @param IN Scanner object for reading user input
 * @param isPasserActive boolean flag to control the main loop
 * @param userInput String to store the latest user input
 *
 */
public class Ui {
    private final Scanner IN;
    private boolean isPasserActive;
    private String userInput;



    public String getUserInput() {
        return userInput;
    }

    /**
     * Constructor for Ui class. Initializes the Scanner and sets the active flag to true.
     */
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
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm JoeBot666");
        System.out.println("What can I do for you today?");
        printLineDivider();
    }

    public void printLineDivider() {
        System.out.println("*************************************");
    }
    public void printGoodbyeMessage() {
        System.out.println("Bye. See you next time!");
        printWelcomeMessage();
    }

}

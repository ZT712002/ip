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

    /**
     * Returns the latest user input.
     * @return userInput String containing the latest user input
     */

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
    /**
     * Reads a line of user input from the console and stores it in the userInput variable.
     */

    public void setUserInput() {
        this.userInput = IN.nextLine();
    }
    /**
     * Returns the current state of the isPasserActive flag.
     * @return true if the main loop should continue, false otherwise
     */

    public boolean isPasserActive() {
        return isPasserActive;
    }
    /**
     * Sets the isPasserActive flag to false, indicating that the main loop should stop.
     */
    public void setIsPasserActiveOff() {
        isPasserActive = false;
    }
    /**
     * Prints a welcome message to the user.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm JoeBot666");
        System.out.println("What can I do for you today?");
        printLineDivider();
    }
    /**
     * Prints a line divider for better readability in the console.
     */
    public void printLineDivider() {
        System.out.println("*************************************");
    }
    /**
     * Prints a goodbye message to the user.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye. See you next time!");
    }

}

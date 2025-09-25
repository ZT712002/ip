package ui;


import java.util.Scanner;

public class Ui {
    private final Scanner IN;
    private boolean isPasserActive;
    private String userInput;



    public String getUserInput() {
        return userInput;
    }

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

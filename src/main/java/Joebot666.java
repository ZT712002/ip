import java.util.Scanner;

public class Joebot666 {
    private static void listItems(String[] storeString) {
        for (int i = 0; i < storeString.length; i++) {
            if (storeString[i] != null) {
                System.out.println((i + 1) + ". " + storeString[i]);
            }
        }
    }
    public static void insertItem(String[] storeString, String userInput){
        for(int i = 0; i < storeString.length; i++){
            if(storeString[i] == null){
                storeString[i] = userInput;
                System.out.println("You have added " + userInput + " to your reading list.");
                break;
            }
        }

    }
    public static void main(String[] args) {
        System.out.println("Hello I'm JoeBot666");
        System.out.println("What can I do for you today?");
        String []storeString = new String[100];
        Scanner in = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("*************************************");
            userInput = in.nextLine();
            if (userInput.contains("list"))  {
                listItems(storeString);
            }else  {
                insertItem(storeString, userInput);
            }
        }
        while(!userInput.equals("bye"));
        System.out.println("Bye. See you next time!");
        System.out.println("*************************************");
    }



}

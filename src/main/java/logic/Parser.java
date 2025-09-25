package logic;

public class Parser {
    public static Command parseCommand(String input) {
        String[] parts = input.split(" ", 2);
        String commandType = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";
        return new Command(commandType, arguments);
    }
}

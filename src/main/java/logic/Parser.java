package logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * The Parser class is responsible for parsing user input commands and date/time strings.
 * It provides methods to extract the command type and arguments from a command string,
 * as well as to convert date/time strings into CustomDate objects.
 */


public class Parser {
    /**
     * Method that takes in input string and parses it into command type and arguments.
     * @param input
     * @return Command object containing command type and arguments.
     */
    public static Command parseCommand(String input) {
        String[] parts = input.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String arguments = parts.length >1 ? parts[1]: "";
        return new Command(commandType, arguments);
    }

    /**
     * Method that takes in date/time string in the format "dd/MM/yyyy HHmm" and converts it into a CustomDate object.
     * @param dateTimeString
     * @return
     */
    public static CustomDate parseDate(String dateTimeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
            return new CustomDate(localDateTime.toLocalDate(), localDateTime.toLocalTime());
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format: " + dateTimeString);
            return null;
        }
    }
}

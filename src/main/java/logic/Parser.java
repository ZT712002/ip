package logic;



public class Parser {
    public static Command parseCommand(String input) {
        String[] parts = input.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String arguments = parts.length >1 ? parts[1]: "";
        return new Command(commandType, arguments);
    }
    public static CustomDate parseDate(String dateTimeString){
        String[] dateTimeParts = dateTimeString.split(" ");
        String datePart = dateTimeParts[0];
        String timePart = dateTimeParts.length > 1 ? dateTimeParts[1] : "0000";

        String[] dateComponents = datePart.split("/");
        int year = Integer.parseInt(dateComponents[2]);
        int month = Integer.parseInt(dateComponents[1]);
        int day = Integer.parseInt(dateComponents[0]);

        int hour = Integer.parseInt(timePart.substring(0, 2));
        int minute = Integer.parseInt(timePart.substring(2, 4));

        return new CustomDate(java.time.LocalDate.of(year, month, day), java.time.LocalTime.of(hour, minute));
    }
}

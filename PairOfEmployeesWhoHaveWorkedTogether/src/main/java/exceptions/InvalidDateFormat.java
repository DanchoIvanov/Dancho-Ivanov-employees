package exceptions;

public class InvalidDateFormat extends Exception {
    public InvalidDateFormat(String errorMessage) {
        super(errorMessage);
    }
}

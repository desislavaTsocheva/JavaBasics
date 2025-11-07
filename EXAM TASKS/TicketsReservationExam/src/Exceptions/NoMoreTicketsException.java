package Exceptions;

public class NoMoreTicketsException extends Exception {
    private String message;

    public NoMoreTicketsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

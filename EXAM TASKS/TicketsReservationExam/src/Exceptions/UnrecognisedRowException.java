package Exceptions;

public class UnrecognisedRowException extends Exception {
    private String message;

    public UnrecognisedRowException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

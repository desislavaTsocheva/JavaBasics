package Exceptions;

public class NoMoreTicketsException extends RuntimeException {
    public NoMoreTicketsException(String message) {
        super(message);
    }
    @Override
    public String getMessage(){
        return "No more tickets available";
    }
}

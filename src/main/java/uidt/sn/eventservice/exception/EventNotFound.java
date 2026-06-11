package uidt.sn.eventservice.exception;

public class EventNotFound extends RuntimeException {
    public EventNotFound(String message) {
        super(message);
    }
}
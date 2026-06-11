package uidt.sn.eventservice.exception;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String error;
    private StringBuffer path;

    public ErrorResponse(String message, int status, String error, StringBuffer path) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
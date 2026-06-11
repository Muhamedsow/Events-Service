package uidt.sn.eventservice.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uidt.sn.eventservice.exception.ErrorResponse;
import uidt.sn.eventservice.exception.EventCompleted;
import uidt.sn.eventservice.exception.EventNotFound;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EventCompleted.class)
    public ResponseEntity<ErrorResponse> handleProduitNotFoundException(EventCompleted ex,
                                                                        HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(ex.getMessage(),
                409,
                "Conflict",
                request.getRequestURL()));
    }

    @ExceptionHandler(EventNotFound.class)
    public ResponseEntity<ErrorResponse> handleProduitReferenceExistException(EventNotFound ex,
                                                                              HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage(),
                404,
                "Not Found",
                request.getRequestURL()));
    }
}
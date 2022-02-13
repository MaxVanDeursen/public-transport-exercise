package nl.maxvandeursen.ticketing;

import nl.maxvandeursen.ticketing.exception.InvalidTicketDateException;
import nl.maxvandeursen.ticketing.exception.PurchaseConditionViolationException;
import nl.maxvandeursen.ticketing.exception.UndefinedTravelerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({InvalidTicketDateException.class, UndefinedTravelerException.class, PurchaseConditionViolationException.class})
    public ResponseEntity<Map<String, Object>> handleErroneousRequests(Exception exception) {
        return createResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UndefinedTravelerException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundExceptions(Exception exception) {
        return createResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Map<String, Object>> createResponseEntity(Exception exception, HttpStatus status) {
        Map<String, Object> values = new HashMap<>();
        values.put("timestamp", ZonedDateTime.now());
        values.put("message", exception.getMessage());
        return new ResponseEntity<>(values, status);
    }
}

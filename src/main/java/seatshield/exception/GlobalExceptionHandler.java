package seatshield.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SeatAlreadyTakenException.class)
    public ResponseEntity<Map<String, String>> handleSeatAlreadyTaken(
            SeatAlreadyTakenException exception) {

        Map<String, String> response = Map.of(
                "error", "SEAT_ALREADY_TAKEN",
                "message", exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
}
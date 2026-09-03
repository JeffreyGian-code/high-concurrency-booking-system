package seatshield.exception;

public class SeatAlreadyTakenException extends RuntimeException {

    public SeatAlreadyTakenException(Long seatId) {
        super("Seat " + seatId + " is already taken");
    }
}
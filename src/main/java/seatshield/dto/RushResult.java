package seatshield.dto;

public record RushResult(
        int successfulBookings,
        int failedBookings
) {
}
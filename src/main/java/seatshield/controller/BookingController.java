package seatshield.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seatshield.entity.Seat;
import seatshield.service.BookingService;

@RestController
@RequestMapping("/api/book")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{seatId}")
    public ResponseEntity<Seat> bookSeat(
            @PathVariable Long seatId) {

        Seat bookedSeat = bookingService.bookSeat(seatId);

        return ResponseEntity.ok(bookedSeat);
    }
}
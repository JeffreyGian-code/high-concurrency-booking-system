package seatshield.controller;


import seatshield.entity.Seat;
import seatshield.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Seat createSeat(@RequestParam String seatNumber) {
        return seatService.createSeat(seatNumber);
    }
}
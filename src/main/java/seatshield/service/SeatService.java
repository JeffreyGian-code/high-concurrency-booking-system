package seatshield.service;


import seatshield.entity.Seat;
import seatshield.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Seat createSeat(String seatNumber) {
        Seat seat = new Seat(seatNumber);
        return seatRepository.save(seat);
    }
}
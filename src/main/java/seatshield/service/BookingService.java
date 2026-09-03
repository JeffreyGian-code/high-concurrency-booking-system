package seatshield.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import seatshield.entity.Seat;
import seatshield.repository.SeatRepository;

@Service
public class BookingService {

    private final SeatRepository seatRepository;

    public BookingService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Transactional
    public Seat bookSeat(Long seatId) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() ->
                        new RuntimeException("Seat not found"));

        if (seat.isBooked()) {
            throw new RuntimeException("Seat is already booked");
        }

        seat.setBooked(true);

        return seatRepository.save(seat);
    }
}
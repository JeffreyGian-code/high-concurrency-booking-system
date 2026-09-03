package seatshield.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seatshield.entity.Seat;
import seatshield.exception.SeatAlreadyTakenException;
import seatshield.repository.SeatRepository;

@Service
public class BookingService {

    private final SeatRepository seatRepository;

    public BookingService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Transactional
    public Seat bookSeat(Long seatId) {

        Seat seat = seatRepository.findSeatForUpdate(seatId)
                .orElseThrow(() ->
                        new RuntimeException("Seat not found"));

        if (seat.isBooked()) {
            throw new SeatAlreadyTakenException(seatId);
        }

        seat.setBooked(true);

        return seatRepository.save(seat);
    }
}
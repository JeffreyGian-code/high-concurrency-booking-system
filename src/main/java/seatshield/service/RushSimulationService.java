package seatshield.service;

import org.springframework.stereotype.Service;
import seatshield.dto.RushResult;
import seatshield.exception.SeatAlreadyTakenException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RushSimulationService {

    private final BookingService bookingService;

    public RushSimulationService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public RushResult simulateRush(Long seatId) {

        int threadCount = 50;

        AtomicInteger successfulBookings = new AtomicInteger();
        AtomicInteger failedBookings = new AtomicInteger();

        CountDownLatch ready = new CountDownLatch(threadCount);
        CountDownLatch start = new CountDownLatch(1);

        try (ExecutorService executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<?>> futures = new ArrayList<>();

            for (int i = 0; i < threadCount; i++) {

                futures.add(executor.submit(() -> {

                    ready.countDown();

                    try {
                        // Wait until all threads are ready
                        start.await();

                        bookingService.bookSeat(seatId);

                        successfulBookings.incrementAndGet();

                    } catch (SeatAlreadyTakenException e) {

                        failedBookings.incrementAndGet();

                    } catch (Exception e) {

                        System.err.println(
                                "Thread failed: " + e.getMessage()
                        );

                        failedBookings.incrementAndGet();
                    }
                }));
            }

            // Wait until all 50 threads are ready
            ready.await();

            System.out.println("🔥 Releasing 50 Virtual Threads!");

            // Release all threads simultaneously
            start.countDown();

            // Wait for completion
            for (Future<?> future : futures) {
                future.get();
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Rush simulation failed", e
            );
        }

        return new RushResult(
                successfulBookings.get(),
                failedBookings.get()
        );
    }
}
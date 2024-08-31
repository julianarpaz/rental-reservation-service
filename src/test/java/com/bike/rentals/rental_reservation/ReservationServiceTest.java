package com.bike.rentals.rental_reservation;

import com.bike.rentals.rental_reservation.entity.Reservation;
import com.bike.rentals.rental_reservation.repository.ReservationRepository;
import com.bike.rentals.rental_reservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService service;

    @MockBean
    private ReservationRepository repository;

    @Test
    public void ReservationServiceTesting(){
        var reservation = new Reservation();
        reservation.setStartTime(LocalDateTime.of(2024, 8, 30, 22, 8, 15));
        service.createReservation(reservation);
        assertEquals(reservation.getEndTime(), LocalDateTime.of(2024, 8, 30, 22, 18, 15));
        Mockito.verify(repository, Mockito.times(1)).save(reservation);
    }

}

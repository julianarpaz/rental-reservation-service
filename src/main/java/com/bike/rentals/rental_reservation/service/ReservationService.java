package com.bike.rentals.rental_reservation.service;

import com.bike.rentals.rental_reservation.entity.Reservation;
import com.bike.rentals.rental_reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {

        if (reservation.getStartTime() == null) {
            reservation.setStartTime(LocalDateTime.now());
        }

        reservation.setEndTime(reservation.getStartTime().plusMinutes(10));
        return reservationRepository.save(reservation);
    }
}


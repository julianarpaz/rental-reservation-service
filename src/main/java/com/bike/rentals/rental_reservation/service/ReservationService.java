package com.bike.rentals.rental_reservation.service;

import com.bike.rentals.rental_reservation.entity.Reservation;
import com.bike.rentals.rental_reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        // Aqui pode ser feita a validação da disponibilidade da bike, regras de negócio, etc.
        //checar se bike ta disponível!!!
        reservation.setEndTime(reservation.getStartTime().plusMinutes(10)); // Exemplo de tempo limite
        return reservationRepository.save(reservation);
    }
}


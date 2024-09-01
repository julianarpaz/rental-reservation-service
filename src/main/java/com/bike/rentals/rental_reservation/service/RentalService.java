package com.bike.rentals.rental_reservation.service;

import com.bike.rentals.rental_reservation.entity.Rental;
import com.bike.rentals.rental_reservation.entity.Reservation;
import com.bike.rentals.rental_reservation.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.time.Duration;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public Rental startRental(Reservation reservation) {
        // Lógica para iniciar um aluguel com base na reserva existente
        // TODO Incluir validação e criação do aluguel

        Rental rental = new Rental();
        rental.setReservationId(reservation.getId());
        rental.setUserId(reservation.getUserId());
        rental.setStartTime(LocalDateTime.now());

        return rentalRepository.save(rental);
    }

    public Rental completeRental(UUID id) {
        // TODO verificar se preciso fazer a busca no banco no controller
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        rental.setEndTime(LocalDateTime.now());
        rental.setCost(calculateCost(rental));
        return rentalRepository.save(rental);
    }

    private BigDecimal calculateCost(Rental rental) {
        var baseValue = new BigDecimal("10.00");
        var duration = Duration.between(rental.getStartTime(), rental.getEndTime());
        return BigDecimal.valueOf(duration.toMinutes()).multiply(baseValue);
    }
}

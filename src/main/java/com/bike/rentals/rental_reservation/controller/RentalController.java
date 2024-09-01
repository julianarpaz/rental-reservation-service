package com.bike.rentals.rental_reservation.controller;

import com.bike.rentals.rental_reservation.entity.Rental;
import com.bike.rentals.rental_reservation.entity.Reservation;
import com.bike.rentals.rental_reservation.repository.RentalRepository;
import com.bike.rentals.rental_reservation.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalRepository repository;

    @PostMapping
    public ResponseEntity<Rental> startRental(@RequestBody Reservation reservation) {
        Rental rental = rentalService.startRental(reservation);
        return new ResponseEntity<>(rental, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRental(@PathVariable UUID id) {
        return repository.findById(id).map(ResponseEntity:: ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Rental> completeRental(@PathVariable UUID id) {
        Rental rental = rentalService.completeRental(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }
}

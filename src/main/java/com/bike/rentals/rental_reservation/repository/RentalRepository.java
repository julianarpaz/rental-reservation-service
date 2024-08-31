package com.bike.rentals.rental_reservation.repository;

import com.bike.rentals.rental_reservation.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {
}

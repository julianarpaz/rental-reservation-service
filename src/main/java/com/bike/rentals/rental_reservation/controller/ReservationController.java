package com.bike.rentals.rental_reservation.controller;

import com.bike.rentals.rental_reservation.entity.PaymentIntention;
import com.bike.rentals.rental_reservation.entity.Reservation;
import com.bike.rentals.rental_reservation.repository.ReservationRepository;
import com.bike.rentals.rental_reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository repository;

//    @Value("${SPRING_PAYMENT_SERVICE_URL}")
//    private String paymentServiceUrl;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        reservationService.createReservation(reservation);
//        RestTemplate restTemplate = new RestTemplate();
//        String paymentUrl = "http://payment-service:8083/mercadopago/checkout";
//        ResponseEntity<PaymentIntention> response = restTemplate.postForEntity(paymentUrl, null, PaymentIntention.class);
//        return ResponseEntity.ok(response.getBody());
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable UUID id) {
        return repository.findById(id).map(ResponseEntity:: ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void removeReservation(@PathVariable UUID id){
        Optional<Reservation> reservation = repository.findById(id);
        if (reservation.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

}

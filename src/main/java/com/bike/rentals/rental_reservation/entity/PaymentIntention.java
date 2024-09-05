package com.bike.rentals.rental_reservation.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentIntention {

    private String id;
    private String sandbox_init_point;
    private UUID reservation_id;
}

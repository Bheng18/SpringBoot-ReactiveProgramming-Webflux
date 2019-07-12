package com.reactiveprog.reactivespring.controller;

import com.reactiveprog.reactivespring.model.Reservation;
import com.reactiveprog.reactivespring.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
@CrossOrigin(origins = "*")
public class ReservationResource {

    public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation/";

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Reservation> getReservationById(@PathVariable String id){
        //reservationService.getReservation(roomId);
        return reservationService.getReservation(id);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux<Reservation> getAllReservations(){
        return reservationService.listAllReservations();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation){
        return reservationService.createReservation(reservation);
    }

    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Reservation> updateReservation(@PathVariable String id, @RequestBody Mono<Reservation> reservation){
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping(path = "{id}")
    public Mono<Boolean> deleteReservation(@PathVariable String id){
        return reservationService.delete(id);
    }

}

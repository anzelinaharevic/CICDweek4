package com.example.passengerservice.controller;

import com.example.passengerservice.model.Passenger;
import com.example.passengerservice.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService service; //constructor DI

    public PassengerController(PassengerService service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<Passenger>> findAll() {return ResponseEntity.ok(service.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getOne(@PathVariable String id) {
        Optional<Passenger> maybe = service.findById(id);
        if (maybe.isPresent()) {
            return ResponseEntity.ok(maybe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger p) {
        Passenger created = service.create(p);
        return ResponseEntity
                .created(URI.create("/api/passengers/" + created.getPassengerId()))
                .body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Passenger> update(@Valid @RequestBody Passenger passenger) {
        Optional<Passenger> maybePassenger = service.update(passenger);
        if (maybePassenger.isPresent()) {
            return ResponseEntity.ok(maybePassenger.get());
        }
        else  {
            return ResponseEntity.notFound().build();
        }

    }
}

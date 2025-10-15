package com.example.passengerservice.service;
import com.example.passengerservice.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PassengerService {
    private final List<Passenger> store = new ArrayList<>();

    public List<Passenger> findAll() {
        return new ArrayList<>(store); //defensive copy
    }

    public Optional<Passenger> findById(String id) {
        for (Passenger p : store) {
            if (p.getPassengerId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
    public Passenger create(Passenger p) {
        if (findById(p.getPassengerId()).isPresent()) {
            throw new IllegalArgumentException("Passenger ID already exists");
        }
        store.add(p);
        return p;
    }

}

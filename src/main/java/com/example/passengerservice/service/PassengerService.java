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
    public Optional<Passenger> update(Passenger passenger) {
        Optional<Passenger> maybePassenger = findById(passenger.getPassengerId());
        if (maybePassenger.isPresent()) {
            Passenger update = maybePassenger.get();
            update.setName(passenger.getName());
            update.setEmail(passenger.getEmail());
            return Optional.of(update);}
        else{
            return Optional.empty();
        }

    }
    public Optional<Passenger> deleteById(String id) {
        for (Passenger p : store) {
            if (p.getPassengerId().equals(id)) {
                store.remove(p);
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
}

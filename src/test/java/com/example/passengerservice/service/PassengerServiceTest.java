package com.example.passengerservice.service;

import com.example.passengerservice.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerServiceTest {

    private PassengerService service;

    @BeforeEach
    void setUp() {service = new PassengerService();}

    @Test
    void createThenFindById() {
        Passenger p = Passenger.builder()
                .passengerId("P1")
                .name("Anz")
                .email("anz@atu.ie")
                .build();

        service.create(p);

        Optional<Passenger> found = service.findById("P1");
        assertTrue(found.isPresent());
        assertEquals("Anz", found.get().getName());
    }

    @Test
    void duplicateIdThrows(){
        service.create(Passenger.builder()
                .passengerId("P2")
                .name("Bob")
                .email("bob@atu.ie")
                .build());

        assertThrows(IllegalArgumentException.class, () ->
                service.create(Passenger.builder()
                        .passengerId("P2")
                        .name("Bobby")
                        .email("bob@gmail.com")
                        .build()));
    }

    @Test
    void UpdatePassenger(){
        Passenger passenger = new Passenger("P4", "hello", "hello@atu.ie");

        service.create(passenger);

        Passenger updatedPassenger = new Passenger("P4", "helloworld", "hello@gmail.com");
        Optional<Passenger> updatedpassenger = service.update(updatedPassenger);
        assertTrue(updatedpassenger.isPresent());
        assertEquals("helloworld", updatedpassenger.get().getName());
        assertEquals("hello@gmail.com", updatedpassenger.get().getEmail());
    }

    @Test
    void deletePassenger(){
        Passenger passenger = new Passenger("P5", "Joy", "Joy@atu.ie");

        service.create(passenger);

        Optional<Passenger> deletePassnger = service.deleteById("P5");

        assertTrue(deletePassnger.isPresent());
        assertEquals("Joy", deletePassnger.get().getName());
        assertEquals("Joy@atu.ie", deletePassnger.get().getEmail());
    }

}

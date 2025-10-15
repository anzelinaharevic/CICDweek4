package com.example.passengerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Passenger {
    @NotBlank @Size(max = 40)
    private String passengerId;

    @NotBlank @Size(max = 60)
    private String name;

    @NotBlank @Email
    private String email;
}


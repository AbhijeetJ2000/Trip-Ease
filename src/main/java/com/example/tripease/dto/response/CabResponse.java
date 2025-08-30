package com.example.tripease.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CabResponse {
    private String cab_number;
    private String cab_model;
    private double per_km_rate;
    private boolean available;
    private DriverResponse driver;
}


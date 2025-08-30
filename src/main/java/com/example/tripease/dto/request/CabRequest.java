package com.example.tripease.dto.request;
import com.example.tripease.dto.response.DriverResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CabRequest {
    private String cab_number;
    private String cab_model;
    private double per_km_rate;
    private boolean available;
}


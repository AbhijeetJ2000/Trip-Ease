package com.example.tripease.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverStatisticsResponse {
    private int driver_id;
    private LocalDate booked_at;
    private double total_fare_collected;
    private double total_distance_travelled;
}



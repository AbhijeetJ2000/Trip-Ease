package com.example.tripease.dto.response;

import com.example.tripease.Enum.TripStatus;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {
    private String pickup;
    private String destination;
    private double tripDistanceInKm;
    private TripStatus tripStatus;
    private double billAmount;
    Date bookedAt;
    Date lastUpdatedAt;
    CustomerResponse customer;
    CabResponse cab;
}



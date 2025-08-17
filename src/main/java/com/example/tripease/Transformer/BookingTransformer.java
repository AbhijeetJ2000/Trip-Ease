package com.example.tripease.Transformer;

import com.example.tripease.Enum.TripStatus;
import com.example.tripease.Model.Booking;
import com.example.tripease.Model.Cab;
import com.example.tripease.Model.Customer;
import com.example.tripease.Model.Driver;
import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;

public class BookingTransformer {
    public static Booking BookingRequestToBooking(BookingRequest bookingRequest, double perKmRate) {
        return Booking.builder()
                .pickup(bookingRequest.getPickup())
                .destination(bookingRequest.getDestination())
                .tripDistanceInKm(bookingRequest.getTripDistanceInKm())
                .tripStatus(TripStatus.ONGOING)
                .billAmount(bookingRequest.getTripDistanceInKm()*perKmRate)
                .build();
    }

    public static BookingResponse BookingToBookingResponse(Booking booking,
                                                           Customer customer,
                                                           Cab cab,
                                                           Driver driver) {
        return BookingResponse.builder()
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .tripDistanceInKm(booking.getTripDistanceInKm())
                .tripStatus(booking.getTripStatus())
                .billAmount(booking.getBillAmount())
                .bookedAt(booking.getBookedAt())
                .lastUpdatedAt(booking.getLastUpdatedAt())
                .customer(customerTransformer.customerToCustomerResponse(customer))
                .cab(CabTransformer.cabToCabResponse(cab, driver))
                .build();
    }
}



package com.example.tripease.Service;

import com.example.tripease.Exception.DriverNotFoundException;
import com.example.tripease.Model.Booking;
import com.example.tripease.Model.Driver;
import com.example.tripease.Repository.BookingRepository;
import com.example.tripease.Repository.DriverRepository;
import com.example.tripease.Transformer.DriverTransformer;
import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.dto.response.DriverStatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    DriverRepository driverRepository;
    public DriverResponse addDriver(DriverRequest driverRequest) {
        Driver driver  = DriverTransformer.driverRequestToDriver(driverRequest);
        Driver savedDriver = driverRepository.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }

    public DriverStatisticsResponse getDriverStatisticsByBookedAt(int driverId, LocalDate bookedAt) {
        List<Booking> bookings = bookingRepository.findBookingsByDriverAndDate(
                driverId, Date.valueOf(bookedAt));

        double totalFare = 0;
        double totalDistance = 0;

        for (Booking booking : bookings) {
            totalFare += booking.getBillAmount();
            totalDistance += booking.getTripDistanceInKm();
        }
        return DriverStatisticsResponse.builder()
                .driver_id(driverId)
                .booked_at(bookedAt)
                .total_fare_collected(totalFare)
                .total_distance_travelled(totalDistance)
                .build();
    }

    public void deleteDriver(Long driverId) {
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        if(driverOptional.isEmpty())
            throw new DriverNotFoundException("Driver with id " + driverId + " not found.");
        driverRepository.deleteById(driverId);
    }
}






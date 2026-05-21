package com.example.tripease.Repository;

import com.example.tripease.Model.Booking;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Booking b JOIN Driver d ON b MEMBER OF d.bookings " +
            "WHERE d.driver_id = :driverId AND DATE(b.bookedAt) = :date")
    List<Booking> findBookingsByDriverAndDate(@Param("driverId") int driverId,
                                              @Param("date") java.sql.Date date);
}



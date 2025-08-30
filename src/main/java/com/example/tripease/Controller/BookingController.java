package com.example.tripease.Controller;

import com.example.tripease.Service.BookingService;
import com.example.tripease.Service.DriverService;
import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    private DriverService driverService;

    @PostMapping("/book/customer/{customerId}")
    public BookingResponse bookCab(@RequestBody BookingRequest bookingRequest,
                                   @PathVariable("customerId") int customerId){
        return bookingService.bookCab(bookingRequest, customerId);
    }
}



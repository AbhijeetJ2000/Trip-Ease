package com.example.tripease.Service;

import com.example.tripease.Exception.CabUnavailableException;
import com.example.tripease.Exception.CustomerNotFoundException;
import com.example.tripease.Model.Booking;
import com.example.tripease.Model.Cab;
import com.example.tripease.Model.Customer;
import com.example.tripease.Model.Driver;
import com.example.tripease.Repository.BookingRepository;
import com.example.tripease.Repository.CabRepository;
import com.example.tripease.Repository.CustomerRepository;
import com.example.tripease.Repository.DriverRepository;
import com.example.tripease.Transformer.BookingTransformer;
import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.dto.response.DriverStatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookingResponse bookCab(BookingRequest bookingRequest, int customerId) {
//        First we will check if there is a valid customer
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }
        Customer customer = optionalCustomer.get();
//        We will find all the cabs that are available.
        Cab availableCab = cabRepository.getAvailableCabRandomly();
        if(availableCab == null){
            throw new CabUnavailableException("Sorry No Cabs Available");
        }
        Booking booking = BookingTransformer.BookingRequestToBooking(bookingRequest, availableCab.getPer_km_rate());
        Booking savedBooking = bookingRepository.save(booking);

        availableCab.setAvailable(false);
        customer.getBookings().add(savedBooking);
        Driver driver = driverRepository.getDriverByCabId(availableCab.getCab_id());
        driver.getBookings().add(savedBooking);

        Customer savedCustomer = customerRepository.save(customer);
        Driver savedDriver = driverRepository.save(driver);

        sendEmail(savedCustomer);

        return BookingTransformer.BookingToBookingResponse(savedBooking, savedCustomer, availableCab, savedDriver);
    }

    private void sendEmail(Customer customer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("abhijeetjha020000@gmail.com");
        mailMessage.setTo(customer.getEmailId());
        mailMessage.setSubject("Cab Booked");
        String text = "Congrats " + customer.getName() + " your cab is booked";
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }
}



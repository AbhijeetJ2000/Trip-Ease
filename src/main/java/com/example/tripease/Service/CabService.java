package com.example.tripease.Service;
import com.example.tripease.Exception.DriverNotFoundException;
import com.example.tripease.Model.Cab;
import com.example.tripease.Model.Driver;
import com.example.tripease.Repository.DriverRepository;
import com.example.tripease.Transformer.CabTransformer;
import com.example.tripease.dto.request.CabRequest;
import com.example.tripease.dto.response.CabResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CabService {
    @Autowired
    DriverRepository driverRepository;
    public CabResponse register(CabRequest cabRequest, int driverId) {
//        First step is to verify that a driver exists or not.
        Optional<Driver> optionalDriver = driverRepository.findById(Long.valueOf(driverId));
        if (optionalDriver.isEmpty()) {
            throw new DriverNotFoundException("Invalid Driver Id");
        }
        Driver driver = optionalDriver.get();
        Cab cab = CabTransformer.cabRequestToCab(cabRequest);
        driver.setCab(cab);
        Driver savedDriver = driverRepository.save(driver);
        return CabTransformer.cabToCabResponse(savedDriver.getCab(), savedDriver);
    }
}



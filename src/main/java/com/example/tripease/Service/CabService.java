package com.example.tripease.Service;
import com.example.tripease.Exception.DriverNotFoundException;
import com.example.tripease.Model.Cab;
import com.example.tripease.Model.Driver;
import com.example.tripease.Repository.CabRepository;
import com.example.tripease.Repository.DriverRepository;
import com.example.tripease.Transformer.CabTransformer;
import com.example.tripease.dto.request.CabRequest;
import com.example.tripease.dto.response.CabResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.tripease.Transformer.CabTransformer.cabToCabResponse;
import static com.example.tripease.Transformer.CabTransformer.cabToCabResponseWithDriver;

@Service
public class CabService {
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    CabRepository cabRepository;

    public CabResponse addCab(CabRequest cabRequest) {
        Cab cab = CabTransformer.cabRequestToCab(cabRequest);
        Cab savedCab = cabRepository.save(cab);
        return cabToCabResponse(savedCab);
    }

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
        return cabToCabResponseWithDriver(savedDriver.getCab(), savedDriver);
    }

    public List<CabResponse> getBudgetCabs(double perKmRate) {
        List<Cab> cabs = cabRepository.findBudgetFriendlyCabs(perKmRate);
        if (cabs.isEmpty())
            return new ArrayList<>();
        List<CabResponse> cabResponses = new ArrayList<>();
        for(Cab cab: cabs){
            cabResponses.add(cabToCabResponse(cab));
        }
        return cabResponses;
    }
}



package com.example.tripease.Transformer;

import com.example.tripease.Model.Driver;
import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;

public class DriverTransformer {
    public static Driver driverRequestToDriver(DriverRequest driverRequest){
        return Driver.builder()
                .name(driverRequest.getName())
                .age(driverRequest.getAge())
                .emailId(driverRequest.getEmailId())
                .build();
    }

    public static DriverResponse driverToDriverResponse(Driver driver){
        return DriverResponse.builder()
                .driverId(driver.getDriver_id())
                .name(driver.getName())
                .age(driver.getAge())
                .emailId(driver.getEmailId())
                .build();
    }
}


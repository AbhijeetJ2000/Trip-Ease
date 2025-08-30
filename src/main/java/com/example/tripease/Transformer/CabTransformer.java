package com.example.tripease.Transformer;

import com.example.tripease.Model.Cab;
import com.example.tripease.Model.Driver;
import com.example.tripease.dto.request.CabRequest;
import com.example.tripease.dto.response.CabResponse;

public class CabTransformer {
    public static Cab cabRequestToCab(CabRequest cabRequest){
        return Cab.builder()
                .cab_number(cabRequest.getCab_number())
                .cab_model(cabRequest.getCab_model())
                .per_km_rate(cabRequest.getPer_km_rate())
                .available(true)
                .build();
    }

    public static CabResponse cabToCabResponse(Cab cab, Driver driver) {
        return CabResponse.builder()
                .cab_number(cab.getCab_number())
                .per_km_rate(cab.getPer_km_rate())
                .cab_model(cab.getCab_model())
                .available(cab.isAvailable())
                .driver(DriverTransformer.driverToDriverResponse(driver))
                .build();
    }
}



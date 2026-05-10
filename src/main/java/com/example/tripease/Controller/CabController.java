package com.example.tripease.Controller;
import com.example.tripease.Model.Cab;
import com.example.tripease.Service.CabService;
import com.example.tripease.dto.request.CabRequest;
import com.example.tripease.dto.response.CabResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cab")
public class CabController {
    @Autowired
    CabService cabService;

    @PostMapping("/add")
    public ResponseEntity<CabResponse> addCab(@RequestBody CabRequest cabRequest) {
        CabResponse response = cabService.addCab(cabRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/register/driver/{driverId}")
    public CabResponse registerCab(@RequestBody CabRequest cabRequest,
                                  @PathVariable("driverId") int driverId) {
        return cabService.register(cabRequest, driverId);
    }

    @GetMapping("/budget-search")
    public ResponseEntity<List<CabResponse>> getBudgetCabs(@RequestParam("per_km_rate") double per_km_rate){
        List<CabResponse> availableCabs = cabService.getBudgetCabs(per_km_rate);
        if(availableCabs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(availableCabs, HttpStatus.OK);
    }
}



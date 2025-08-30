package com.example.tripease.Controller;
import com.example.tripease.Service.DriverService;
import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.dto.response.DriverStatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    DriverService driverService;
    @PostMapping("/add")
    public DriverResponse addDriver(@RequestBody DriverRequest driverRequest){
        return driverService.addDriver(driverRequest);
    }

    @GetMapping("/stats")
    public DriverStatisticsResponse getDriverDailyStats(
            @RequestParam("driverId") int driverId,
            @RequestParam("date") String date) {

        LocalDate localDate = LocalDate.parse(date);
        return driverService.getDriverStatisticsByBookedAt(driverId, localDate);
    }
}


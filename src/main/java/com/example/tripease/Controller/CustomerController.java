package com.example.tripease.Controller;

import com.example.tripease.Enum.Gender;
import com.example.tripease.Service.CustomerService;
import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.addCustomer(customerRequest);
    }

    @GetMapping("/get/customer-id/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") int customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping("/get/customer/{gender}")
    public List<CustomerResponse> getCustomersByGender(@PathVariable("gender") Gender gender) {
        return customerService.getAllByGender(gender);
    }

    @GetMapping("/get")
    public List<CustomerResponse> getAllCustomersByGenderAndAge(@RequestParam("gender") Gender gender
            , @RequestParam("age") int age) {
        return customerService.getAllByGenderAndAge(gender, age);
    }

    @GetMapping("/get-by-age-greater-than")
    public List<CustomerResponse> getAllByGenderAndAgeGreaterBy(@RequestParam("gender") Gender gender
            , @RequestParam("age") int age) {
        return customerService.getAllByGenderAndAgeGreaterBy(gender, age);
    }
}






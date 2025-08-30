package com.example.tripease.Service;

import com.example.tripease.Enum.Gender;
import com.example.tripease.Exception.CustomerNotFoundException;
import com.example.tripease.Model.Customer;
import com.example.tripease.Repository.CustomerRepository;
import com.example.tripease.Transformer.customerTransformer;
import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
//    Request DTO to Entity conversion
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
//      Request DTO -> Saved Entity
        Customer customer = customerTransformer.customerRequestToCustomer(customerRequest);
//      save the entity in DB
        Customer savedCustomer = customerRepository.save(customer);
//      Saved Entity -> Response DTO
        CustomerResponse customerResponse = customerTransformer.customerToCustomerResponse(customer);
        return customerResponse;
    }

    public CustomerResponse getCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }
        Customer savedCustomer = optionalCustomer.get();
//      Saved Entity -> Response DTO
        return customerTransformer.customerToCustomerResponse(savedCustomer);
    }

    public List<CustomerResponse> getAllByGender(Gender gender) {
        List<Customer> customers = customerRepository.findByGender(gender);
//        Entity -> ResponseDTO
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponse customerResponse = customerTransformer.customerToCustomerResponse(customer);
            customerResponseList.add(customerResponse);
        }
        return customerResponseList;
    }

    public List<CustomerResponse> getAllByGenderAndAge(Gender gender, int age) {
        List<Customer> customers = customerRepository.findByGenderAndAge(gender, age);
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponse customerResponse = customerTransformer.customerToCustomerResponse(customer);
            customerResponseList.add(customerResponse);
        }
        return customerResponseList;
    }

    public List<CustomerResponse> getAllByGenderAndAgeGreaterBy(Gender gender, int age) {
        List<Customer> customers = customerRepository.getAllByGenderAndAgeGreaterBy(gender, age);
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponse customerResponse = customerTransformer.customerToCustomerResponse(customer);
            customerResponseList.add(customerResponse);
        }
        return customerResponseList;
    }
}



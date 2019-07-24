package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.SignupCustomerRequest;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerResponse;
import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
import com.upgrad.FoodOrderingApp.service.businness.signUpService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService sup;
    @RequestMapping(value="/customer/signup",method= RequestMethod.POST)
    public ResponseEntity<SignupCustomerResponse> signup(SignupCustomerRequest s){
        CustomerEntity c=new CustomerEntity();
        c.setUuid(UUID.randomUUID().toString());
        c.setFirstname(s.getFirstName());
        c.setLastname(s.getLastName());
        c.setContact_number(s.getContactNumber());
        c.setEmail(s.getEmailAddress());
        c.setPassword(s.getPassword());
        c.setSalt("1234@abc");
        final CustomerEntity createdUserEntity=sup.saveCustomer(c);
        SignupCustomerResponse userResponse=new SignupCustomerResponse().id(createdUserEntity.getUuid());
        return new ResponseEntity<SignupCustomerResponse>(userResponse, HttpStatus.CREATED);
    }




}

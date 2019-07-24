package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.SaveAddressRequest;
import com.upgrad.FoodOrderingApp.api.model.SaveAddressResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @RequestMapping(value = "/address",method= RequestMethod.POST)
    public ResponseEntity<SaveAddressResponse>saveAddress(SaveAddressRequest s){

    }
    public ResponseEntity<>getStateByUUID(String s){

    }
}

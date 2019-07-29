package com.upgrad.FoodOrderingApp.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.AddressService;
import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
import com.upgrad.FoodOrderingApp.service.dao.addressDao;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
public class AddressController {
    @RequestMapping(value = "/address",method= RequestMethod.POST)
    public ResponseEntity<SaveAddressResponse>saveAddress(SaveAddressRequest s,@RequestHeader("authorization")final String authentication)throws Exception{
            CustomerEntity c=CustomerService.getCustomer(authentication);
            AddressEntity a=new AddressEntity();
            a.setCity(s.getCity());
            a.setUuid(s.getStateUuid());
            a.setFlatBuilNo(s.getFlatBuildingName());
            a.setLocality(s.getLocality());
            a.setPincode(s.getPincode());
            a=AddressService.saveAddress(a,c);
            SaveAddressResponse aur=new SaveAddressResponse().id(a.getUuid()).status("ADDRESS SUCCESSFULLY REGISTERED");
            return new ResponseEntity<SaveAddressResponse>(aur, HttpStatus.OK);
    }
    @RequestMapping(value = "/address/customer",method= RequestMethod.GET)
    public ResponseEntity<AddressListResponse>getAllAddress(@RequestHeader("authorization")final String authentication)throws Exception{
        CustomerEntity c=CustomerService.getCustomer(authentication);
        List<AddressEntity>list=AddressService.getAllAddress(c);
        String response=list.toString();
        final AddressListResponse addressLists = new ObjectMapper().readValue(response, AddressListResponse.class);
        return new ResponseEntity<AddressListResponse>(addressLists, HttpStatus.OK);
    }
    @RequestMapping(value = "/address/{address_id}",method= RequestMethod.DELETE)
    public ResponseEntity<DeleteAddressResponse>deleteAddress(@PathVariable("address_id") String address_id,@RequestHeader("authorization")final String authentication)throws Exception{
        CustomerEntity c=CustomerService.getCustomer(authentication);
        AddressEntity ar=new AddressEntity();
        if(address_id!=c.getUuid())
            throw new AuthorizationFailedException("ATHR-004","You are not authorized to view/update/delete any one else's address");
        AddressService.deleteAddress(addressDao.getAddress(address_id));
        DeleteAddressResponse aur=new DeleteAddressResponse().id(UUID.fromString(address_id));
        return new ResponseEntity<DeleteAddressResponse>(aur,HttpStatus.OK);
    }
}

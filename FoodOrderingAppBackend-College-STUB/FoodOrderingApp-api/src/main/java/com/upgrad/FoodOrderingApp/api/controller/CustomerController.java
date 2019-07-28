package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import com.upgrad.FoodOrderingApp.service.exception.UpdateCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService sup;
    @RequestMapping(value="/customer/signup",method= RequestMethod.POST)
    public ResponseEntity<SignupCustomerResponse> signup(SignupCustomerRequest s)throws SignUpRestrictedException {
        CustomerEntity c=new CustomerEntity();
        c.setUuid(UUID.randomUUID().toString());
        c.setFirstname(s.getFirstName());
        c.setLastname(s.getLastName());
        c.setContact_number(s.getContactNumber());
        c.setEmail(s.getEmailAddress());
        c.setPassword(s.getPassword());
        c.setSalt("1234@abc");
        final CustomerEntity createdUserEntity=sup.saveCustomer(c);
        SignupCustomerResponse userResponse=new SignupCustomerResponse().id(createdUserEntity.getUuid()).status("CUSTOMER SUCCESSFULLY REGISTERED");
        return new ResponseEntity<SignupCustomerResponse>(userResponse, HttpStatus.CREATED);
    }
    @RequestMapping(value="/customer/login",method=RequestMethod.POST)
    public ResponseEntity<LoginResponse>login(@RequestHeader("authorization")final String authentication)throws AuthenticationFailedException {
        byte[] decoded= Base64.getDecoder().decode(authentication);
        String decodedText=new String(decoded);
        if(decodedText.indexOf(":")==-1)
            throw new AuthenticationFailedException("ATH-003","Incorrect format of decoded customer name and password");
        String[] decodedArray=decodedText.split(":");
        CustomerAuthEntity userAuthTokenEntity=CustomerService.authenticate(
                decodedArray[0],decodedArray[1]
        );
        CustomerEntity user=CustomerAuthEntity.getCustomer();
        LoginResponse aur=new LoginResponse().id(user.getUuid())
                .emailAddress(user.getEmail()).firstName(user.getFirstname())
                .lastName(user.getLastname()).contactNumber(user.getContact_number()).message("LOGGED IN SUCCESSFULLY");
        HttpHeaders httpHeader=new HttpHeaders();
        httpHeader.add("access-token",userAuthTokenEntity.getAccessToken());
        return new ResponseEntity<LoginResponse>(aur,httpHeader,HttpStatus.OK);

    }
    @RequestMapping(value="/customer/logout",method=RequestMethod.POST)
    public ResponseEntity<LogoutResponse>logout(@RequestHeader("authorization")final String authentication)throws AuthorizationFailedException {
        String decodedText=authentication;
        CustomerAuthEntity c=CustomerService.logout(decodedText);
        LogoutResponse lr=new LogoutResponse().id(c.getUuid()).message("LOGGED OUT SUCCESSFULLY");
        return new ResponseEntity<LogoutResponse>(lr,HttpStatus.OK);
    }
    @RequestMapping(value="/customer/password",method=RequestMethod.PUT)
    public ResponseEntity<UpdatePasswordResponse>updateCustomerPassword(@RequestHeader("authorization")final String oldpwd,final String newpwd,final CustomerEntity c)throws UpdateCustomerException {

        CustomerEntity p=sup.updateCustomerPassword(oldpwd,newpwd,c);
        UpdatePasswordResponse lr=new UpdatePasswordResponse().id(p.getUuid()).status("CUSTOMER PASSWORD UPDATED SUCCESSFULLY");
        return new ResponseEntity<UpdatePasswordResponse>(lr,HttpStatus.OK);
    }




}

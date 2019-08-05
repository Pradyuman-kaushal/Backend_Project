package com.upgrad.FoodOrderingApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    @RequestMapping(value="/restaurant/name/{reastaurant_name}",method= RequestMethod.GET)
    public ResponseEntity <>restaurantsByName()

}

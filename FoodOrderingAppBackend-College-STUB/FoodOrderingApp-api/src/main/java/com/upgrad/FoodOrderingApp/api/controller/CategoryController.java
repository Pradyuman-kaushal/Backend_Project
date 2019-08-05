package com.upgrad.FoodOrderingApp.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.AddressService;
import com.upgrad.FoodOrderingApp.service.businness.CategoryService;
import com.upgrad.FoodOrderingApp.service.businness.ItemService;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CategoryController {
    @Autowired
    private static CategoryService cs;
    @RequestMapping(value="/category",method = RequestMethod.GET)
    public ResponseEntity<CategoriesListResponse>getAllCategoriesOrderedByName()throws Exception {
        List<CategoryEntity> list = cs.getAllCategoriesOrderedByName();
        String response = list.toString();
        final CategoriesListResponse categoryLists = new ObjectMapper().readValue(response, CategoriesListResponse.class);
        return new ResponseEntity<CategoriesListResponse>(categoryLists, HttpStatus.OK);
    }
    @RequestMapping(value="/category/{category_id}",method = RequestMethod.GET)
    public ResponseEntity<CategoryDetailsResponse>getCategoryById(@PathVariable("category_id") String category_id)throws Exception{
        List<ItemEntity>list= ItemService.getItemByCategoryId(category_id);
        String response=list.toString();
        final List<ItemList> itemLists = list.stream()
                .map(developer -> new ItemList().id(UUID.fromString(developer.getUuid())).itemName(developer.getItemName()).price(developer.getPrice()))
                .collect(Collectors.toList());
        CategoryEntity p=cs.getCategoryById(category_id);
        CategoryDetailsResponse cl=new CategoryDetailsResponse().id(UUID.fromString(p.getUuid())).categoryName(p.getCategoryName()).itemList(itemLists);
        return new ResponseEntity<CategoryDetailsResponse>(cl, HttpStatus.OK);
    }
}

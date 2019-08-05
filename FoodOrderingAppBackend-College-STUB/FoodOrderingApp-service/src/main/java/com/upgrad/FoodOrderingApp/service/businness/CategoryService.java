package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.categoryDao;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import org.aspectj.weaver.patterns.TypeCategoryTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private static categoryDao ct;
    public static List<CategoryEntity> getAllCategoriesOrderedByName(){
        List<CategoryEntity>lt=ct.getAllCategory();
        return lt;
    }
    public static CategoryEntity getCategoryById(String id)throws CategoryNotFoundException{
        if(id.length()==0)
            throw new CategoryNotFoundException("CNF-001","Category id field should not be empty");
        if(ct.getCategoryId(id)==null)
            throw new CategoryNotFoundException("CNF-002","No category by this id");
        return ct.getCategoryId(id);
    }
}

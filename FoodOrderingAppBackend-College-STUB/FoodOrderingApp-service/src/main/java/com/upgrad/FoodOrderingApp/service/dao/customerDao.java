package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class customerDao {
    @PersistenceContext
    private EntityManager entityManager;
    public CustomerEntity createUser(CustomerEntity userEntity){
        entityManager.persist(userEntity);
        return userEntity;
    }
}

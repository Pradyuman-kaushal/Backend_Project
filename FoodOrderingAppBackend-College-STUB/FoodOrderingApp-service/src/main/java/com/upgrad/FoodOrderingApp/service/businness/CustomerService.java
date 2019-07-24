package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.customerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    @Autowired
    private customerDao userDao;
    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity saveCustomer(CustomerEntity userEntity){
        String[] encrypted=passwordCryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setPassword(encrypted[1]);
        userEntity.setSalt(encrypted[0]);
        return userDao.createUser(userEntity);
    }
    public CustomerAuthEntity authencate(String no,String pass){

    }
}

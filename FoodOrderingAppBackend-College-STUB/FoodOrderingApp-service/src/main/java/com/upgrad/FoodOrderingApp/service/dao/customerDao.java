package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class customerDao {
    @PersistenceContext
    private static EntityManager entityManager;
    private static EntityManagerFactory emf;
    public CustomerEntity createUser(CustomerEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }
    public static boolean checkContact(String contact_number){
        CustomerEntity s=null;
        entityManager=emf.createEntityManager();
        s=entityManager.find(CustomerEntity.class,contact_number);
        entityManager.close();
        if(s==null)
        return true;
        else
            return false;
    }
    public static CustomerEntity getUserByContact(String username) {
        try {
            CustomerEntity userEntity = entityManager.createNamedQuery("userByContact", CustomerEntity.class)
                    .setParameter("contact_number", username).getSingleResult();
            return userEntity;
        } catch (NoResultException e) {
            return null;
        }
    }

    public static void updateCustomer(CustomerEntity updatedUser) {
        entityManager.merge(updatedUser);
    }

    public CustomerEntity getCustomer(final String access_token) {
        try {
            CustomerEntity userEntity = entityManager.createNamedQuery("userByToken", CustomerEntity.class)
                    .setParameter("access_token", access_token).getSingleResult();
            return userEntity;
        }catch (NoResultException e){
            return null;
        }
    }
}



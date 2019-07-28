package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class customerAuthTokenDao {
    @PersistenceContext
    private static EntityManager entityManager;

    public static CustomerAuthEntity create(final CustomerAuthEntity newToken) {
        entityManager.persist(newToken);
        return newToken;
    }

    public static CustomerAuthEntity getAuthTokenByAccessToken(String authToken) {
        try {
            return entityManager.createNamedQuery("userAuthTokenByAccessToken", CustomerAuthEntity.class).setParameter("accessToken", authToken).getSingleResult();
        } catch (NoResultException e) {

            return null;

        }
    }
    public static void updatedCustomer(CustomerAuthEntity updatedUser) {
        entityManager.merge(updatedUser);
    }
}

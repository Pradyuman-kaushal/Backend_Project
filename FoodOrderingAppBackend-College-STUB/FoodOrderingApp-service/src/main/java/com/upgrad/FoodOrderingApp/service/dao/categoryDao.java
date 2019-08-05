package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.CategoryItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class categoryDao {
    private EntityManagerFactory emf;

    public List<CategoryEntity> getAllCategory() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<CategoryEntity> query = em.createQuery("Select p from CategoryEntity p orderBy p.category_name", CategoryEntity.class);
        List<CategoryEntity> resultList = query.getResultList();
        return resultList;
    }
    public CategoryEntity getCategoryId(String id){
        CategoryEntity s=null;
        EntityManager em=emf.createEntityManager();
        TypedQuery<CategoryEntity> query = em.createQuery("SELECT p FROM CategoryEntity p, CategoryItemEntity n WHERE p.id = n.category_id AND n.category_id=uid", CategoryEntity.class).setParameter("uid",id);
        s=query.getSingleResult();
        em.close();
        return s;
    }

}

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
public class itemDao {
    private EntityManagerFactory emf;
    public List<ItemEntity> getItemsByCategory(String cat_id){
        EntityManager em = emf.createEntityManager();
        TypedQuery<ItemEntity> query = em.createNamedQuery("SELECT p FROM ItemEntity p, CategoryItemEntity n WHERE p.id = n.item_id AND n.category_id=uid", ItemEntity.class).setParameter("uid",cat_id);
        List<ItemEntity> resultList = query.getResultList();
        return resultList;
    }
}

package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class addressDao {

        @PersistenceContext
        private static EntityManager entityManager;
        private static EntityManagerFactory emf;
        public static AddressEntity addAddress(AddressEntity userEntity) {
            entityManager.persist(userEntity);
            return userEntity;
        }
        public static StateEntity getState(String uuid){
            StateEntity s=null;
            EntityManager em=emf.createEntityManager();
            s=em.find(StateEntity.class,uuid);
            em.close();
            return s;
        }
        public static List<AddressEntity> getAllAddress(CustomerEntity c){

            EntityManager em=emf.createEntityManager();
            TypedQuery<AddressEntity> query=em.createQuery("Select p from AddressEntity p where p.uuid=c.getUuid()",AddressEntity.class);
            List<AddressEntity> resultList=query.getResultList();
            return resultList;
        }
        public static AddressEntity delete(AddressEntity id)throws AddressNotFoundException{
            AddressEntity s=null;
            AddressEntity p=null;
            EntityManager em=emf.createEntityManager();
            s=em.find(AddressEntity.class,id);
            s=p;
            if(s==null)
                throw new AddressNotFoundException("ANF-003","No address by this id");
            em.remove(s);
            em.close();
            return p;
        }
        public static AddressEntity getAddress(String id)throws AddressNotFoundException{
            AddressEntity s=null;
            EntityManager em=emf.createEntityManager();
            s=em.find(AddressEntity.class,id);
            if(s==null)
                throw new AddressNotFoundException("ANF-003","No address by this id");
            em.close();
            return s;
        }
    }

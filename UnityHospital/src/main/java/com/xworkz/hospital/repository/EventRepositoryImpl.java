package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
@Repository
public class EventRepositoryImpl  implements EventRepository{
    @Autowired
    EntityManagerFactory entityManagerFactory;


    @Override
    public boolean save(EventEntity eventEntity) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(eventEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return false;
    }

}

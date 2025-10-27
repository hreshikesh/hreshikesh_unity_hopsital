package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

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

    @Override
    public List<EventEntity> getAllEvent() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        List<EventEntity> entities=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("getAllEvent");
            entities=query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return entities;
    }

    @Override
    public boolean deleteEvent(int id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            EventEntity eventEntity=manager.find(EventEntity.class,id);
            if(eventEntity!=null){
                manager.remove(eventEntity);
            }
            transaction.commit();
            return  true;
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

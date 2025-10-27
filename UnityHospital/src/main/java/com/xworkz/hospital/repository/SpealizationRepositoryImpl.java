package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.EventEntity;
import com.xworkz.hospital.entity.SpecializationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class SpealizationRepositoryImpl implements  SpecializationRepository {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveSpecialization(SpecializationEntity entity) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(entity);
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
    public List<SpecializationEntity> getAllSpecialization() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        List<SpecializationEntity> entities=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("getAllSpecialization");
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
    public boolean deleteSpecialization(int id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
           SpecializationEntity specializationEntity=manager.find(SpecializationEntity.class,id);
            if(specializationEntity!=null){
                manager.remove(specializationEntity);
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

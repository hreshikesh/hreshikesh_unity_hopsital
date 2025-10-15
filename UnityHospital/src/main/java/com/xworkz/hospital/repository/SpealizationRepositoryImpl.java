package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.SpecializationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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
}

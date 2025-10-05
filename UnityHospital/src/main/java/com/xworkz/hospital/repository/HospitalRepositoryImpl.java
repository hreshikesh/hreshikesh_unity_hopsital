package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
@Repository
public class HospitalRepositoryImpl implements HospitalRepository {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public int findEmail(String email) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        int count = 0;
        try {
            transaction.begin();
            Query query = manager.createNamedQuery("emailCount");
            query.setParameter("email", email);
            long countEmail = (long) query.getSingleResult();
            count = Math.toIntExact(countEmail);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return count;
    }

    @Override
    public HospitalEntity findByEmail(String email) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        HospitalEntity hospital = null;
        try {
            transaction.begin();
            Query query = manager.createNamedQuery("findByEmail");
            query.setParameter("email", email);
            hospital = (HospitalEntity) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return hospital;
    }

    @Override
    public void updateTable(HospitalEntity entity) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
    }



    @Override
    public List<HospitalEntity> getAllWithOtp() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        List<HospitalEntity> hospitalEntities = null;
        try {
            transaction.begin();
            hospitalEntities = manager.createNamedQuery("findAllOTP").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return hospitalEntities;
    }


}

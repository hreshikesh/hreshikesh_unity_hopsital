package com.xworkz.hospital.repository;

import com.xworkz.hospital.dto.PatientDto;
import com.xworkz.hospital.entity.BloodGroupEntity;
import com.xworkz.hospital.entity.PateintEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PatientRepositoryImpl  implements PatientRepository{
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public List<BloodGroupEntity> getAllBloodGroup() {
        EntityManager manager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=manager.getTransaction();
        List<BloodGroupEntity> bloodGroupEntities=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("getBloodGroupList");
            bloodGroupEntities=query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return bloodGroupEntities;
    }

    @Override
    public String getTimeSlot(String email) {
        EntityManager manager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=manager.getTransaction();
        String interval="";
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("getInterval");
            query.setParameter("email",email);
            interval=(String) query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return interval;
    }

    @Override
    public boolean savePatientDetails(PateintEntity entity) {
        EntityManager manager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=manager.getTransaction();
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

package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.DoctorTimeSlotEntity;
import com.xworkz.hospital.entity.TimeSlotEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
@Slf4j
public class SlotRepositoryImpl  implements  SlotRepository{
    @Autowired
    EntityManagerFactory entityManagerFactory;


    @Override
    public boolean saveTimeInterval(TimeSlotEntity entity) {
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
            }e.printStackTrace();
        } finally {
            manager.close();
        }
        return false;
    }

    @Override
    public List<DoctorEntity> findDoctorBySpecialization(String specialization) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        List<DoctorEntity> doctors=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("doctorBySpecialization");
            query.setParameter("specializationBy",specialization);
            doctors=query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return doctors;
    }

    @Override
    public List<TimeSlotEntity> findAllIntervals(String specialization) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        List<TimeSlotEntity> entities=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("findAllTImeSlots");
            query.setParameter("specializationBy",specialization);
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
    public boolean setTimeSlot(DoctorTimeSlotEntity entity) {
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
    public long checkIntervalForSpecification(String specialization, String timeInterval) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        long count=0L;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("checkInterval");
            query.setParameter("timeSlot",timeInterval);
            query.setParameter("specializationBy",specialization);
            count=(long)query.getSingleResult();
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
    public long checkInterval(String email, String interval) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction=manager.getTransaction();
        long count=0L;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("checkSlot");
            query.setParameter("email",email);
            query.setParameter("interval",interval);
            count=(long)query.getSingleResult();
            transaction.commit();
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }e.printStackTrace();
        }finally {
            manager.close();
        }
        return count;
    }

}

package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.BloodGroupEntity;
import com.xworkz.hospital.entity.DoctorTimeSlotEntity;
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
    public List<DoctorTimeSlotEntity> getTimeSlot(int  id) {
        EntityManager manager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=manager.getTransaction();
        List<DoctorTimeSlotEntity> interval=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("getInterval");
            query.setParameter("id",id);
            interval=query.getResultList();

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

    @Override
    public DoctorTimeSlotEntity getInterval(int id) {
        EntityManager manager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=manager.getTransaction();
        DoctorTimeSlotEntity interval=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("getIntervalById");
            query.setParameter("id",id);
            interval=(DoctorTimeSlotEntity) query.getSingleResult();
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
    public List<PateintEntity> getPatient(int id, int slotId) {
        EntityManager manager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=manager.getTransaction();
        List<PateintEntity> pateintEntities=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("getPatientByDoctorId");
            query.setParameter("id",id);
            query.setParameter("slotId",slotId);
           pateintEntities= query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return pateintEntities;
    }

    @Override
    public PateintEntity getPatientDetails(int id) {
        EntityManager manager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=manager.getTransaction();
        PateintEntity pateintEntity=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("getPatientByPatientId");
            query.setParameter("id",id);
            pateintEntity=(PateintEntity) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return pateintEntity;
    }
}

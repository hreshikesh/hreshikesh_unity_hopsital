package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.HospitalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

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
    public boolean saveDoctor(DoctorEntity entity) {
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
    public DoctorEntity searchByEmail(String email) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        DoctorEntity doctorEntity=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("findByName");
            query.setParameter("email",email);
            doctorEntity=(DoctorEntity)query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return doctorEntity;
    }

    @Override
    public boolean updateDoctor(DoctorEntity entity) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
           DoctorEntity entity1= searchByEmail(entity.getDoctorEmail());
           entity1.setDoctorName(entity.getDoctorName());
           entity1.setDoctorPhone(entity.getDoctorPhone());
           entity1.setSpecialization(entity.getSpecialization());
           entity1.setQualification(entity.getQualification());
           entity1.setExperience(entity.getExperience());
           entity1.setImagePath(entity.getImagePath());
           manager.merge(entity1);
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
    public List<DoctorEntity> getAllDoctor() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        List<DoctorEntity> entities=null;
        try {
            transaction.begin();
            Query query=manager.createNamedQuery("getAllDoctor");
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



}

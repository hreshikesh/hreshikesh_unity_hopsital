package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.SpecializationEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
public class DoctorRepositoryImpl  implements  DoctorRepository{
    @Autowired
    EntityManagerFactory entityManagerFactory;



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
    public List<SpecializationEntity> getAllSpecialization() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        List<SpecializationEntity> entity=new ArrayList<>();
        try {
            transaction.begin();
            Query query = manager.createNamedQuery("getAllSpecilaization",SpecializationEntity.class);
            entity = query.getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return entity;
    }



    @Override
    public boolean deleteDoctor(String email) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            Query query = manager.createNamedQuery("findByName");
            query.setParameter("email", email);
            DoctorEntity entity = (DoctorEntity) query.getSingleResult();
            manager.remove(entity);
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
    public long getEmailCount(String email) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        long count = 0L;
        try {
            transaction.begin();
            Query query = manager.createNamedQuery("doctorEmailCount");
            query.setParameter("email", email);
            count = (long) query.getSingleResult();
            log.info(String.valueOf(count));
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

    @Override
    public boolean updateDoctor(DoctorEntity entity) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            log.info(String.valueOf(entity.getId()));
            manager.merge(entity);
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
    public DoctorEntity findById(int id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        DoctorEntity entity = null;
        try {
            transaction.begin();
            Query query = manager.createNamedQuery("getDoctorDetailsById");
            query.setParameter("id", id);
            entity = (DoctorEntity) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
        return entity;
    }




}

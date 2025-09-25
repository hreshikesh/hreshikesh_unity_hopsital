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
    public List<HospitalEntity> getAllWithOtp() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction=manager.getTransaction();
        List<HospitalEntity> hospitalEntities=null;
        try {
            transaction.begin();
            hospitalEntities= manager.createNamedQuery("findAllOTP").getResultList();
            transaction.commit();
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }e.printStackTrace();
        }finally {
            manager.close();
        }
        return hospitalEntities;
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

package com.xworkz.hospital.repository;

import com.xworkz.hospital.dto.UserDto;
import com.xworkz.hospital.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

@Repository
public class UserRepositoryImpl implements  UserRepository{
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public long checkEmail(String email) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        long count = 0L;
        try {
            transaction.begin();
            Query query = manager.createNamedQuery("UserEmailCheck");
            query.setParameter("email", email);
            count = (long) query.getSingleResult();
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
    public boolean saveUser(UserEntity userEntity) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(userEntity);
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
    public long checkMobileNumber(long phone) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        long count = 0L;
        try {
            transaction.begin();
            Query query = manager.createNamedQuery("UserPhoneCheck");
            query.setParameter("phone", phone);
            count = (long) query.getSingleResult();
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
    public UserEntity findByEmail(String email) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        UserEntity entity=null;
        try {
            transaction.begin();
            Query query = manager.createNamedQuery("getEntityByEmail");
            query.setParameter("email", email);
            entity= (UserEntity) query.getSingleResult();
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
    public void updateTable(UserEntity entity) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        UserEntity entity1=null;
        try {
            transaction.begin();
            Query query = manager.createNamedQuery("getEntityByEmail");
            query.setParameter("email", entity.getUserEmail());
            entity1= (UserEntity) query.getSingleResult();

            entity1.setOtp(entity.getOtp());
            entity1.setLoginTime(entity.getLoginTime());

            manager.merge(entity1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }
    }
}

package com.xworkz.hospital.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
            count=Math.toIntExact(countEmail);
        }catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
        }finally {
            manager.close();
        }
        return count;
    }
}

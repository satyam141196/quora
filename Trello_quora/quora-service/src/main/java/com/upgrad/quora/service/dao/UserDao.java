package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public UserEntity createUser(UserEntity userEntity){
        entityManager.persist(userEntity);
        return userEntity;
    }

    public UserEntity getUserByEmail(final String userEmail) {
        try {
            return entityManager.createNamedQuery("userByEmail", UserEntity.class).setParameter("email", userEmail)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }

    public void deleteUser(final UserEntity userEntity) {
        entityManager.remove(userEntity);
    }

    public UserEntity getUserByUserName(final String useName) {
        try {
            return entityManager.createNamedQuery("userByName", UserEntity.class).setParameter("username", useName)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }
}

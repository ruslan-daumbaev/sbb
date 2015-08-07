package com.tsystems.sbb.DAL.implementation;

import com.tsystems.sbb.DAL.contracts.UsersRepository;
import com.tsystems.sbb.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        return null;
    }

    public User findUserByName(String userName) {
         return entityManager.createQuery("select u from User u where loginName=:login", User.class).
                 setParameter("login", userName).getSingleResult();
    }
}

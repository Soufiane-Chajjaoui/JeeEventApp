package com.example.demo.services;

import com.example.demo.entities.User;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
@Local
public class AuthService {
    EntityManagerFactory emf;
    EntityManager em;

    public AuthService() {
        emf = Persistence.createEntityManagerFactory("EventPU");
        em = emf.createEntityManager();
    }

    public void register(User user) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(user);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User login(String email, String password) {
        try{
        User user = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        if (user != null) {
            return user;
        }
    } catch( NoResultException e)
    {
        return null;
    }
    return null;
}

    public User findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email ", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }
}

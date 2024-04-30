package com.example.demo.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class ConcretRepository<T , ID extends Number> implements Repository<T , ID>{
    EntityManagerFactory emf;
    EntityManager em;

    public ConcretRepository() {
        emf = Persistence.createEntityManagerFactory("EventPU");
        em = emf.createEntityManager();
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<T>> findAll() {
        return Optional.empty();
    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public T save(T object) {
        return null;
    }
}

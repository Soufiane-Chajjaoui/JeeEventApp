package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID extends Number> {
    public Optional<T> findById(ID id);
    public Optional<List<T>> findAll();
    public void deleteById(ID id);
    public T save(T object);
}

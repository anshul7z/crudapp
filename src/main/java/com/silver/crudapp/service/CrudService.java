package com.silver.crudapp.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    List<T> getAll();
    T getById(Long uid);
    T save(T t);
    T update(T t);
    boolean delete(Long uid);
    boolean deleteAll();
}

package com.epam.rd.autocode.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, Id> {

    Optional<T> getById(Id Id);

    List<T> getAll();

    T save(T t);

    void delete(T t);
}


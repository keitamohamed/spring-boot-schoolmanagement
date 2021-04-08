package com.keita.schoolmanagement.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T, ID extends Serializable> {

    T save(T entry);

    Optional<T> find(ID id) throws SQLException;

    List<T> findAll();

    Long findIdById(Long id);
}

package com.keita.schoolmanagement.dao;

import com.keita.schoolmanagement.model.Authenticate;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T, ID> {

//    Authenticate auth(String username, String password) throws SQLException;
    Optional<T> find(ID id) throws SQLException;
    List<T> findAll() throws SQLException;
    boolean sava(T o) throws SQLException;
    boolean update(T o) throws SQLException;
    boolean deleteData(T o) throws SQLException;

}

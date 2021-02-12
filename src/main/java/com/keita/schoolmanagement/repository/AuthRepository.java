package com.keita.schoolmanagement.repository;

import com.keita.schoolmanagement.dao.DAO;
import com.keita.schoolmanagement.model.Authenticate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AuthRepository extends DAO<Authenticate, Long>, JpaRepository<Authenticate, Long> {

//    @Override
    @Query(value = "SELECT * FROM Authenticate INNER JOIN Role ON " +
            "Authenticate.authID = Role.userRoleID WHERE username " +
            "= :username and password = :password", nativeQuery = true)
    Authenticate auth(@Param("username") String username, @Param("password") String password) throws SQLException;

    @Override
    default Optional<Authenticate> find(Long aLong) throws SQLException {
        return Optional.empty();
    }

    @Override
    default List<Authenticate> findAll() {
        return null;
    }

    @Override
    default void deleteById(Long aLong) {

    }

    @Override
    default List<Authenticate> findAll(Sort sort) {
        return null;
    }

    @Override
    default <S extends Authenticate> S save(S s) {
        return null;
    }

    @Override
    default <S extends Authenticate> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    default Optional<Authenticate> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    default <S extends Authenticate> S saveAndFlush(S s) {
        return null;
    }

    @Override
    default boolean sava(Authenticate o) throws SQLException {
        return false;
    }

    @Override
    default boolean update(Authenticate o) throws SQLException {
        return false;
    }

    @Override
    default boolean deleteData(Authenticate o) throws SQLException {
        return false;
    }

    @Override
    default void deleteAll() {

    }
}

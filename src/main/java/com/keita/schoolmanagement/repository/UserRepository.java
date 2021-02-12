package com.keita.schoolmanagement.repository;

import com.keita.schoolmanagement.dao.DAO;
import com.keita.schoolmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends DAO<User, Long>, JpaRepository<User, Long> {

    @Override
    default Optional<User> find(Long aLong) throws SQLException {
        return Optional.empty();
    };

    @Override
   default List<User> findAll() {
        return new ArrayList<>();
    };

    @Override
    @Modifying
    @Query(value = "INSERT INTO User (userID, firstName, lastName, gender, dateOfBirth, age, phoneNum, email) " +
            "values (:userID, :firstName, :lastName, :gender, :dateOFBirth, :age, :phoneNum, :email)", nativeQuery = true)
   default boolean sava(User user) throws SQLException {
        return true;
    };

    @Override
    default boolean update(User o) throws SQLException {
        return false;
    };

    @Override
    default boolean deleteData(User o) throws SQLException {
        return false;
    };
}

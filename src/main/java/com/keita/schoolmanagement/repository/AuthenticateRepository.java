package com.keita.schoolmanagement.repository;

import com.keita.schoolmanagement.dao.DAO;
import com.keita.schoolmanagement.model.Authenticate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;

public interface AuthenticateRepository extends DAO<Authenticate, Long>, JpaRepository<Authenticate, Long> {

    @Override
    @Query(value = "Select userAuthID FROM Authenticate auth WHERE auth.authID = :authID", nativeQuery = true)
    Long findIdById(@Param("authID") Long authID);

    @Query(value = "SELECT * FROM Authenticate WHERE username " +
            "= :username and password = :password", nativeQuery = true)
    Authenticate checkLogin(@Param("username") String username, @Param("password") String password) throws SQLException;

    @Override
    @Query(value = "SELECT * FROM Authenticate as auth WHERE auth.userAuthID = :userID", nativeQuery = true)
    Optional<Authenticate> findById(@Param("userID") Long userID);

    @Modifying()
    @Query(value = "INSERT INTO Authenticate (userAuthID, username, userRole, password, conformPassword) " +
            "VALUES(:userAuthID, :username, :userRole, :password, :conformPassword)", nativeQuery = true)
    @Transactional
    void save(@Param("userAuthID") Long userAuthID, @Param("username") String username,
              @Param("userRole") String userRole,
              @Param("password") String password,
              @Param("conformPassword") String conformPassword);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Authenticate a SET a.username = :username, a.password = :password, " +
            "a.conformPassword = :conformPassword, a.userRole = :userRole WHERE a.userAuthID = :userId", nativeQuery = true)
    void updateData(@Param("userId") Long userId, @Param("username") String username,
                    @Param("password") String password, @Param("conformPassword") String conformPassword, @Param("userRole") String userRole);

    @Override
    default Optional<Authenticate> find(Long aLong) throws SQLException {
        return Optional.empty();
    }

}

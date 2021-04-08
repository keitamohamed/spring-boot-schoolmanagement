package com.keita.schoolmanagement.repository;

import com.keita.schoolmanagement.dao.DAO;
import com.keita.schoolmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends DAO<User, Long>, JpaRepository<User, Long> {

    @Override
    @Query(value = "SELECT userID FROM User u WHERE u.userID = :userID", nativeQuery = true)
    Long findIdById(@Param("userID") Long id);

    User findByEmail(String email);

    @Override
    User save(User entry);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, " +
            "u.gender = :gender, u.age = :age, u.phoneNum = :phoneNum, " +
            "u.dateOfBirth = :dob, u.email = :email  WHERE u.userID = :userID", nativeQuery = true)
    void updateData(@Param("userID") Long userID, @Param("firstName") String firstName,
                    @Param("lastName") String lastName, @Param("gender") String gender,
                    @Param("age") int age, @Param("phoneNum") String phoneNum, @Param("dob") Date date,
                    @Param("email") String email);

    @Override
    default Optional<User> find(Long aLong) throws SQLException {
        return Optional.empty();
    }

    @Query(value = "SELECT * FROM User AS u " +
            "INNER JOIN Authenticate AS a ON " +
            "u.userID = a.userAuthID WHERE a.userRole = 'Student'", nativeQuery = true)
    List<User> findAllByRole();
}

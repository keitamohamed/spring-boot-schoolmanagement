package com.keita.schoolmanagement.repository;

import com.keita.schoolmanagement.dao.DAO;
import com.keita.schoolmanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;

public interface AddressRepository extends DAO<Address, Long>, JpaRepository<Address, Long> {

    @Override
    @Query(value = "SELECT userID FROM Address a WHERE a.addressID = :addressID", nativeQuery = true)
    Long findIdById(@Param("addressID") Long addressID);

    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO Address (userID, street, city, state, zipCode) VALUES(:aUserID, :street, :city, :state, :zipCode)", nativeQuery = true)
    @Transactional
    void save(@Param("aUserID") Long aUserID, @Param("street") String street,
              @Param("city") String city, @Param("state") String state,
              @Param("zipCode") int zipCode);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Address a SET a.street = :street, a.city = :city, " +
            "a.state = :state, a.zipCode = :zipCode WHERE a.userID = :userID", nativeQuery = true)
    void updateData(@Param("street") String street, @Param("city") String city,
                    @Param("state") String state, @Param("zipCode") int zipCode,
                    @Param("userID") Long userID);

    @Override
    @Query(value = "SELECT * FROM Address as a WHERE a.userID = :userID", nativeQuery = true)
    Optional<Address> findById(@Param("userID") Long userID);

    @Override
    default Optional<Address> find(Long aLong) throws SQLException {
        return Optional.empty();
    }

    ;
}

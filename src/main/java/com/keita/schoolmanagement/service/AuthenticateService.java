package com.keita.schoolmanagement.service;

import com.keita.schoolmanagement.model.Authenticate;
import com.keita.schoolmanagement.repository.AuthenticateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class AuthenticateService {

    @Autowired
    private AuthenticateRepository authRepository;

    public Long findIdByID(Long id) {
        return authRepository.findIdById(id);
    }

    public Optional<Authenticate> getUserAuth(Long userID) {
        return authRepository.findById(userID);
    }

    public Authenticate auth(String username, String password) throws SQLException {
        return authRepository.checkLogin(username, password);
    }

    public void save(Authenticate userAuth, Long userID) {
        authRepository.save(userID, userAuth.getUsername(), userAuth.getUserRole(),
                userAuth.getPassword(), userAuth.getConformPassword());
    }

    public void updateAuthenticate(Long userID, Authenticate authenticate) {
        authRepository.updateData(userID, authenticate.getUsername(), authenticate.getPassword(),
                authenticate.getConformPassword(), authenticate.getUserRole());
    }
}

package com.keita.schoolmanagement.service;

import com.keita.schoolmanagement.model.Authenticate;
import com.keita.schoolmanagement.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public Authenticate auth(String username, String password) throws SQLException {
        return authRepository.auth(username, password);
    }
}

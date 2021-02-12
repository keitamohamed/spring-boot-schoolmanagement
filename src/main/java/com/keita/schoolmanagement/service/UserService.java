package com.keita.schoolmanagement.service;

import com.keita.schoolmanagement.model.User;
import com.keita.schoolmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save (User user) {
        return userRepository.save(user);
    }
}

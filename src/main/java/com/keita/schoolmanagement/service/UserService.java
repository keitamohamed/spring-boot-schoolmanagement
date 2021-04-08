package com.keita.schoolmanagement.service;

import com.keita.schoolmanagement.model.Authenticate;
import com.keita.schoolmanagement.model.User;
import com.keita.schoolmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User checkEmailValidation(User user) {
        return userRepository.findByEmail(user.getEmail());
    }

    public Long getID(Long id) {
        return userRepository.findIdById(id);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) throws SQLException {
        return userRepository.save(user);
    }

    public void updateUser(Authenticate authenticate, User user) {
        userRepository.updateData(authenticate.getUser().getUserID(), user.getFirstName(),
                user.getLastName(), user.getGender(), user.getAge(),
                user.getPhoneNum(), user.getDateOfBirth(), user.getEmail());
    }

    public Optional<User> findByID(Long userID) {
        return userRepository.findById(userID);
    }

    public List<User> getUsers() {
        return userRepository.findAllByRole();
    }
}

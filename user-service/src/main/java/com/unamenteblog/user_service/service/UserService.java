package com.unamenteblog.user_service.service;

import com.unamenteblog.user_service.model.User;
import com.unamenteblog.user_service.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserByGoogleId(String googleId) {
        return userRepository.findUserByGoogleId(googleId);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

}

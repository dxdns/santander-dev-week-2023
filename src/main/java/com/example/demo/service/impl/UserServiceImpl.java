package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.UserModel;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Override
    public UserModel add(UserModel user) {
        if (userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("user exists");
        }

        return userRepository.save(user);
    }

}

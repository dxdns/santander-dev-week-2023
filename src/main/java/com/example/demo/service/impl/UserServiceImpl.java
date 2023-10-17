package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.UserModel;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserModel getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Override
    @Transactional
    public UserModel add(UserModel user) {
        if(userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new ConflictException("account number already exists");
        }

        if (userRepository.existsByCardNumber(user.getCard().getNumber())) {
            throw new ConflictException("card number already exists");
        }

        return userRepository.save(user);
    }

}

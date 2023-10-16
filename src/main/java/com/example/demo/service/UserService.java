package com.example.demo.service;

import com.example.demo.domain.model.UserModel;

public interface UserService {
    UserModel getById(Long id);

    UserModel add(UserModel user);
}

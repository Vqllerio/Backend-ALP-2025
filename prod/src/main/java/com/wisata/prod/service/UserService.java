package com.wisata.prod.service;

import com.wisata.prod.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Integer idUser);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Integer idUser);

    User findByEmail(String email);
}

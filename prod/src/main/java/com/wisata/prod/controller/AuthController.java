package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email sudah terdaftar!";
        }
        userRepository.save(user);
        return "Akun berhasil dibuat!";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        User user = userRepository.findByEmail(loginUser.getEmail());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            return "Login berhasil!";
        }
        return "Email atau password salah!";
    }
}

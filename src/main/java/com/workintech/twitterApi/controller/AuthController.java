package com.workintech.twitterApi.controller;

import com.workintech.twitterApi.dto.AuthResponse;
import com.workintech.twitterApi.dto.UserRequest;
import com.workintech.twitterApi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")

public class AuthController {

    // TODO POST /register → UserRequest alır, token (String) döner
    // TODO POST /login    → UserRequest alır, token (String) döner

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody UserRequest userRequest){
        return authService.register(userRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest userRequest){
        return authService.login(userRequest);
    }
}

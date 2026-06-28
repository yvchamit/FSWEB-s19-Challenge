package com.workintech.twitterApi.service;

import com.workintech.twitterApi.dto.AuthResponse;
import com.workintech.twitterApi.dto.UserRequest;

public interface AuthService {

    AuthResponse register(UserRequest userRequest); // token döner

    AuthResponse login(UserRequest userRequest);
}

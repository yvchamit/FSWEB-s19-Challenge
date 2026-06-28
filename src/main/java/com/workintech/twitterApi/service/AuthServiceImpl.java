package com.workintech.twitterApi.service;

import com.workintech.twitterApi.dto.AuthResponse;
import com.workintech.twitterApi.dto.UserRequest;
import com.workintech.twitterApi.entity.User;
import com.workintech.twitterApi.exceptions.UserException;
import com.workintech.twitterApi.repository.UserRepository;
import com.workintech.twitterApi.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findByUserName(userRequest.getUserName());
        if (userOptional.isPresent()) {
            throw new UserException("User already exists: " + userRequest.getUserName(), HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return new AuthResponse(jwtUtil.generateToken(user.getUserName()), user.getId(), user.getUserName());
    }

    @Override
    public AuthResponse login(UserRequest userRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getUserName(),
                        userRequest.getPassword()
                )
        );

        User user = userRepository.findByUserName(userRequest.getUserName())
                .orElseThrow(() -> new UserException("User not found", HttpStatus.NOT_FOUND));

        String token = jwtUtil.generateToken(user.getUserName());

        return new AuthResponse(token, user.getId(), user.getUserName());
    }
}

package com.workintech.twitterApi.service;

import com.workintech.twitterApi.entity.User;
import com.workintech.twitterApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User findByUserName(String userName) {
        return userRepo.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found: " + userName));
    }

    @Override
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepo.delete(user);
    }
}

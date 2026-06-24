package com.workintech.twitterApi.service;
import com.workintech.twitterApi.entity.User;

public interface UserService {

    User saveUser(User user);

    User findById(Long id);

    User findByUserName(String userName);

    void deleteUser(Long id);
}

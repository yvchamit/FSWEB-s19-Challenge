package com.workintech.twitterApi.service;

import com.workintech.twitterApi.entity.User;
import com.workintech.twitterApi.exceptions.UserException;
import com.workintech.twitterApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp(){
        user = new User();
        user.setId(1L);
        user.setUserName("ahmet_yilmaz");
        user.setEmail("ahmet.yilmaz@gmail.com");
        user.setPassword("Ahmet123");
    }

    @DisplayName("Should Save User Successfully")
    @Test
    void canSaveUser() {
        when(userRepo.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("ahmet_yilmaz", savedUser.getUserName());

        verify(userRepo, times(1)).save(user);
    }


    @DisplayName("When User Exists Should Return User")
    @Test
    void canFindById() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertNotNull(result);
        assertEquals("ahmet_yilmaz", result.getUserName());
        verify(userRepo, times(1)).findById(1L);
    }

    @DisplayName("When User Not Found Should Throw Exception")
    @Test
    void canNotFindById() {
        when(userRepo.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findById(2L))
                .isInstanceOf(UserException.class)
                .hasMessageContaining("User not found with id");

        verify(userRepo, times(1)).findById(2L);
    }
}
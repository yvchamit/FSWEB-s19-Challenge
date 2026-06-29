package com.workintech.twitterApi.service;

import com.workintech.twitterApi.entity.Tweet;
import com.workintech.twitterApi.entity.User;
import com.workintech.twitterApi.exceptions.TweetException;
import com.workintech.twitterApi.repository.TweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TweetServiceImplTest {

    private Tweet tweet;

    private User user;

    @InjectMocks
    private TweetServiceImpl tweetService;

    @Mock
    private TweetRepository tweetRepo;

    @BeforeEach
    public void setUp(){
        user = new User();
        user.setId(1L);
        user.setUserName("ahmet_yilmaz");
        user.setEmail("ahmet.yilmaz@gmail.com");
        user.setPassword("Ahmet123");

        tweet = new Tweet();
        tweet.setAuthor(user);
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setContent("Merhaba Twitter!");
    }

    @DisplayName("When Tweet Exists Should Return Tweet")
    @Test
    void canFindById() {
        when(tweetRepo.findById(1L)).thenReturn(Optional.of(tweet));

        Tweet result = tweetService.findById(1L);

        assertNotNull(result);

        assertEquals("Merhaba Twitter!", result.getContent());

        verify(tweetRepo, times(1)).findById(1L);
    }

    @DisplayName("When Tweet Not Found Should Throw Exception")
    @Test
    void canNotFindById() {

        when(tweetRepo.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> tweetService.findById(2L))
                .isInstanceOf(TweetException.class)
                .hasMessageContaining("Tweet not found with id");

        verify(tweetRepo, times(1)).findById(2L);
    }

    @DisplayName("Should Save Tweet Successfully")
    @Test
    void canSaveTweet() {

        when(tweetRepo.save(tweet)).thenReturn(tweet);

        Tweet savedTweet = tweetService.saveTweet(tweet);

        assertNotNull(savedTweet);

        assertEquals("Merhaba Twitter!", savedTweet.getContent());

        verify(tweetRepo, times(1)).save(tweet);
    }

    @DisplayName("Should Update Tweet Successfully")
    @Test
    void canUpdateTweet() {

        tweet.setId(3L);

        when(tweetRepo.findById(3L)).thenReturn(Optional.of(tweet));

        Tweet updatedTweet = new Tweet();
        updatedTweet.setContent("Hello World!");

        when(tweetRepo.save(tweet)).thenReturn(tweet);

        tweetService.updateTweet(3L, updatedTweet);

        assertEquals("Hello World!", tweet.getContent());
        verify(tweetRepo, times(1)).save(tweet);
    }

    @DisplayName("Should Delete Tweet Successfully")
    @Test
    void canDeleteTweet() {

        tweet.setId(2L);

        when(tweetRepo.findById(2L)).thenReturn(Optional.of(tweet));

        tweetService.deleteTweet(2L);

        verify(tweetRepo, times(1)).delete(tweet);

    }
}
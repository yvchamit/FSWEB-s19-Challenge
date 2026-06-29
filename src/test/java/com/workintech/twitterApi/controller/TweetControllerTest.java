package com.workintech.twitterApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.twitterApi.converter.TweetConverter;
import com.workintech.twitterApi.dto.TweetRequest;
import com.workintech.twitterApi.dto.TweetResponse;
import com.workintech.twitterApi.dto.UserResponse;
import com.workintech.twitterApi.entity.Tweet;
import com.workintech.twitterApi.entity.User;
import com.workintech.twitterApi.security.JwtFilter;
import com.workintech.twitterApi.security.JwtUtil;
import com.workintech.twitterApi.service.TweetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;




@ExtendWith(MockitoExtension.class)
class TweetControllerTest {

    @Mock
    private TweetService tweetService;

    @Mock
    private TweetConverter tweetConverter;

    private TweetController tweetController;

    private Tweet tweet;
    private TweetRequest tweetRequest;
    private TweetResponse tweetResponse;

    @BeforeEach
    void setUp() {
        tweetController = new TweetController(tweetService, tweetConverter);
        User user = new User();
        user.setId(1L);
        user.setUserName("ahmet_yilmaz");

        tweet = new Tweet();
        tweet.setId(2L);
        tweet.setContent("This is a test");
        tweet.setAuthor(user);

        tweetRequest = new TweetRequest();
        tweetRequest.setUserId(1L);
        tweetRequest.setContent("This is a test");

        tweetResponse = new TweetResponse(
                tweet.getId(),
                tweet.getContent(),
                LocalDateTime.now(),
                tweet.getLikeCount(),
                tweet.getRetweetCount(),
                tweet.getReplyCount(),
                new UserResponse(user.getId(), user.getUserName()),
                null
        );
    }

    @DisplayName("Should Save Tweet Successfully")
    @Test
    void canSaveTweet() {
        when(tweetConverter.toEntity(any(TweetRequest.class))).thenReturn(tweet);
        when(tweetService.saveTweet(any(Tweet.class))).thenReturn(tweet);
        when(tweetConverter.toResponse(any(Tweet.class))).thenReturn(tweetResponse);

        TweetResponse result = tweetController.saveTweet(tweetRequest);

        assertNotNull(result);
        assertEquals("This is a test", result.content());
        verify(tweetService).saveTweet(any(Tweet.class));
    }

    @DisplayName("Should Find Tweet By Id Successfully")
    @Test
    void canFindById() {
        when(tweetService.findById(2L)).thenReturn(tweet);
        when(tweetConverter.toResponse(any(Tweet.class))).thenReturn(tweetResponse);

        TweetResponse result = tweetController.findById(2L);

        assertNotNull(result);
        assertEquals("This is a test", result.content());
        verify(tweetService).findById(2L);
    }

    @DisplayName("Should Delete Tweet Successfully")
    @Test
    void canDeleteTweet() {
        tweetController.deleteTweet(2L);
        verify(tweetService).deleteTweet(2L);
    }

    @DisplayName("Should Update Tweet Successfully")
    @Test
    void canUpdateTweet() {
        when(tweetConverter.toEntity(any(TweetRequest.class))).thenReturn(tweet);
        when(tweetService.updateTweet(anyLong(), any(Tweet.class))).thenReturn(tweet);
        when(tweetConverter.toResponse(any(Tweet.class))).thenReturn(tweetResponse);

        TweetResponse result = tweetController.updateTweet(2L, tweetRequest);

        assertNotNull(result);
        assertEquals("This is a test", result.content());
        verify(tweetService).updateTweet(anyLong(), any(Tweet.class));
    }
}
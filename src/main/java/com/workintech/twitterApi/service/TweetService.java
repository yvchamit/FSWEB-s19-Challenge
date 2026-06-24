package com.workintech.twitterApi.service;

import com.workintech.twitterApi.entity.Tweet;

import java.util.List;

public interface TweetService {

    Tweet saveTweet(Tweet tweet);

    List<Tweet> findByAuthorId(Long authorId);

    Tweet findById(Long id);

    Tweet updateTweet(Long id, Tweet tweet);

    void deleteTweet(Long id);

    Tweet retweet(Long tweetId, Long userId);

    void deleteRetweet(Long id);

}

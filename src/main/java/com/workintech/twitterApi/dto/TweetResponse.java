package com.workintech.twitterApi.dto;

import java.time.LocalDateTime;

public record TweetResponse(Long id, String content, LocalDateTime createdAt, Integer likeCount, Integer retweetCount, Integer replyCount, UserResponse userResponse, TweetResponse retweet) {
}

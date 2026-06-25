package com.workintech.twitterApi.converter;

import com.workintech.twitterApi.dto.TweetRequest;
import com.workintech.twitterApi.dto.TweetResponse;
import com.workintech.twitterApi.dto.UserResponse;
import com.workintech.twitterApi.entity.Tweet;
import com.workintech.twitterApi.service.TweetService;
import com.workintech.twitterApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TweetConverter {

    private final UserService userService;
    private final TweetService tweetService;

    @Autowired
    public TweetConverter(UserService userService, TweetService tweetService) {
        this.userService = userService;
        this.tweetService = tweetService;
    }

    // TODO → Request → Entity
    public Tweet toEntity(TweetRequest request) {
        Tweet tweet = new Tweet();
        tweet.setContent(request.getContent());
        tweet.setAuthor(userService.findById(request.getUserId()));
        return tweet;
    }


    // TODO → Entity → Response
    public TweetResponse toResponse(Tweet tweet) {
        UserResponse authorResponse = new UserResponse(
                tweet.getAuthor().getId(),
                tweet.getAuthor().getUserName()
        );

        TweetResponse retweetResponse = null;
        if (tweet.getRetweet() != null) {
            retweetResponse = new TweetResponse(
                    tweet.getRetweet().getId(),
                    tweet.getRetweet().getContent(),
                    tweet.getRetweet().getCreatedAt(),
                    tweet.getRetweet().getLikeCount(),
                    tweet.getRetweet().getRetweetCount(),
                    tweet.getRetweet().getReplyCount(),
                    authorResponse,
                    null
            );
        }

        return new TweetResponse(
                tweet.getId(),
                tweet.getContent(),
                tweet.getCreatedAt(),
                tweet.getLikeCount(),
                tweet.getRetweetCount(),
                tweet.getReplyCount(),
                authorResponse,
                retweetResponse
        );
    }
}

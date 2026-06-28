package com.workintech.twitterApi.controller;
import com.workintech.twitterApi.dto.LikeRequest;
import com.workintech.twitterApi.entity.Like;
import com.workintech.twitterApi.entity.Tweet;
import com.workintech.twitterApi.service.LikeService;
import com.workintech.twitterApi.service.TweetService;
import com.workintech.twitterApi.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;
    private final UserService userService;
    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<String> like(@RequestBody LikeRequest likeRequest) {
        Like like = new Like();
        like.setUser(userService.findById(likeRequest.getUserId()));

        Tweet tweet = tweetService.findById(likeRequest.getTweetId());
        like.setTweet(tweet);

        tweet.setLikeCount(tweet.getLikeCount() + 1);
        tweetService.saveTweet(tweet);

        likeService.saveLike(like);
        return ResponseEntity.ok("Tweet liked successfully");
    }
}

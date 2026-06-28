package com.workintech.twitterApi.controller;
import com.workintech.twitterApi.dto.LikeRequest;
import com.workintech.twitterApi.entity.Tweet;
import com.workintech.twitterApi.service.LikeService;
import com.workintech.twitterApi.service.TweetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dislike")
@AllArgsConstructor
public class DislikeController {

    private final LikeService likeService;
    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<String> dislike(@RequestBody LikeRequest likeRequest) {
        likeService.deleteLike(likeRequest.getUserId(), likeRequest.getTweetId());

        Tweet tweet = tweetService.findById(likeRequest.getTweetId());
        tweet.setLikeCount(tweet.getLikeCount() - 1);
        tweetService.saveTweet(tweet);

        return ResponseEntity.ok("Tweet disliked successfully");
    }
}

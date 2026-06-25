package com.workintech.twitterApi.controller;

import com.workintech.twitterApi.converter.TweetConverter;
import com.workintech.twitterApi.dto.RetweetRequest;
import com.workintech.twitterApi.dto.TweetRequest;
import com.workintech.twitterApi.dto.TweetResponse;
import com.workintech.twitterApi.entity.Tweet;
import com.workintech.twitterApi.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private TweetService tweetService;

    private TweetConverter tweetConverter;

    @DeleteMapping("/{retweetId}")
    public ResponseEntity<String> deleteRetweet(@PathVariable Long retweetId){
        tweetService.deleteRetweet(retweetId);
        return ResponseEntity.ok("Tweet deleted successfully");
    }

    @PostMapping
    public TweetResponse saveRetweet(@RequestBody RetweetRequest retweetRequest){
        Tweet savedRetweet = tweetService.retweet(retweetRequest.getTweetId(), retweetRequest.getUserId());
        return tweetConverter.toResponse(savedRetweet);
    }
}

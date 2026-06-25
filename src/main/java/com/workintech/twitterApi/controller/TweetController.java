package com.workintech.twitterApi.controller;
import com.workintech.twitterApi.converter.TweetConverter;
import com.workintech.twitterApi.dto.TweetRequest;
import com.workintech.twitterApi.dto.TweetResponse;
import com.workintech.twitterApi.entity.Tweet;
import com.workintech.twitterApi.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    private TweetConverter tweetConverter;

    @PostMapping
    public TweetResponse saveTweet(@RequestBody TweetRequest tweetRequest) {

        Tweet saved = tweetService.saveTweet(tweetConverter.toEntity(tweetRequest));
        return tweetConverter.toResponse(saved);
    }

    @GetMapping("/findByUserId")
    public List<TweetResponse> findByAuthorId(@RequestParam Long userId) {

        List<Tweet> findTweets = tweetService.findByAuthorId(userId);

        List<TweetResponse> responses = new ArrayList<>();
        for(Tweet tweet : findTweets){
            responses.add(tweetConverter.toResponse(tweet));
        }
        return responses;
    }

    @GetMapping("/{findId}")
    public TweetResponse findById(@PathVariable long findId){
        Tweet findTweet = tweetService.findById(findId);
        return tweetConverter.toResponse(findTweet);
    }

    @DeleteMapping("/{deleteId}")
    public ResponseEntity<String> deleteTweet(@PathVariable Long deleteId){
        tweetService.deleteTweet(deleteId);
        return ResponseEntity.ok("Tweet deleted successfully");
    }

    @PutMapping("/{updateId}")
    public TweetResponse updateTweet(@PathVariable Long updateId, @RequestBody TweetRequest tweetRequest) {
        Tweet updateTweet = tweetService.updateTweet(updateId, tweetConverter.toEntity(tweetRequest));
        return tweetConverter.toResponse(updateTweet);
    }

}

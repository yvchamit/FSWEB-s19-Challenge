package com.workintech.twitterApi.controller;
import com.workintech.twitterApi.entity.Tweet;
import com.workintech.twitterApi.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    public Tweet saveTweet(@RequestBody Tweet tweet) {
        return tweetService.saveTweet(tweet);
    }

    @GetMapping("/findByUserId")
    public List<Tweet> findByAuthorId(@RequestParam Long userId) {
        return tweetService.findByAuthorId(userId);
    }


    @GetMapping("/{findId}")
    public Tweet findById(@PathVariable long findId){
        return tweetService.findById(findId);
    }

    @DeleteMapping("/{deleteId}")
    public void deleteTweet(@PathVariable Long deleteId){
        tweetService.deleteTweet(deleteId);
    }

    @PutMapping("/{updateId}")
    public Tweet updateTweet(@PathVariable Long updateId, @RequestBody Tweet tweet){
        return tweetService.updateTweet(updateId, tweet);
    }
}

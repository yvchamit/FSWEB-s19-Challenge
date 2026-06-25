package com.workintech.twitterApi.controller;
import com.workintech.twitterApi.dto.LikeRequest;
import com.workintech.twitterApi.entity.Like;
import com.workintech.twitterApi.service.LikeService;
import com.workintech.twitterApi.service.TweetService;
import com.workintech.twitterApi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;
    private final UserService userService;
    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<String> like(@RequestBody LikeRequest likeRequest) {
        Like like = new Like();
        like.setUser(userService.findById(likeRequest.getUserId()));
        like.setTweet(tweetService.findById(likeRequest.getTweetId()));
        likeService.saveLike(like);
        return ResponseEntity.ok("Tweet liked successfully");
    }
}

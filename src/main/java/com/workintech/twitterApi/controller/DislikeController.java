package com.workintech.twitterApi.controller;
import com.workintech.twitterApi.dto.LikeRequest;
import com.workintech.twitterApi.service.LikeService;
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

    @PostMapping
    public ResponseEntity<String> dislike(@Valid @RequestBody LikeRequest likeRequest) {
        likeService.deleteLike(likeRequest.getUserId(), likeRequest.getTweetId());
        return ResponseEntity.ok("Tweet disliked successfully");
    }
}

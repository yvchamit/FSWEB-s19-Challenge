package com.workintech.twitterApi.service;

import com.workintech.twitterApi.entity.Like;
import com.workintech.twitterApi.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService{


    private LikeRepository likeRepo;

    @Override
    public Like saveLike(Like like) {
        return likeRepo.save(like);
    }

    @Override
    public void deleteLike(Long userId, Long tweetId) {
        Optional<Like> optLike = likeRepo.findByUserIdAndTweetId(userId, tweetId);

        Like deleteLike = optLike.orElseThrow(() -> new RuntimeException("Like not found for userId: " + userId + " and tweetId: " + tweetId));

        likeRepo.delete(deleteLike);
    }
}

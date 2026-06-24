package com.workintech.twitterApi.repository;

import com.workintech.twitterApi.entity.Comment;
import com.workintech.twitterApi.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndTweetId(Long userId, Long tweetId);

    boolean existsByUserIdAndTweetId(Long userId, Long tweetId);
}

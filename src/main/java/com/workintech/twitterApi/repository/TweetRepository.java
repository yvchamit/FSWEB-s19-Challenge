package com.workintech.twitterApi.repository;

import com.workintech.twitterApi.entity.Comment;
import com.workintech.twitterApi.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByAuthorId(Long authorId);
}

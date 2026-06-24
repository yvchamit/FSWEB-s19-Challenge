package com.workintech.twitterApi.repository;
import java.util.List;
import com.workintech.twitterApi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTweetId(Long tweetId);
}

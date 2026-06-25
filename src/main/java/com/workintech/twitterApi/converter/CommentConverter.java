package com.workintech.twitterApi.converter;

import com.workintech.twitterApi.dto.CommentRequest;
import com.workintech.twitterApi.dto.CommentResponse;
import com.workintech.twitterApi.dto.UserResponse;
import com.workintech.twitterApi.entity.Comment;
import com.workintech.twitterApi.service.TweetService;
import com.workintech.twitterApi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentConverter {

    private final UserService userService;
    private final TweetService tweetService;

    public Comment toEntity(CommentRequest request) {
        // TODO Yeni Comment nesnesi oluştur
        Comment comment = new Comment();

        // TODO content'i set et
        comment.setContent(request.getCommentContent());

        // TODO userId ile author'ı bul ve set et
        comment.setAuthor(userService.findById(request.getUserId()));

        // TODO tweetId ile tweet'i bul ve set et
        comment.setTweet(tweetService.findById(request.getTweetId()));

        // TODO return et
        return  comment;
    }

    public CommentResponse toResponse(Comment comment) {

        // TODO comment'in author'ından UserResponse oluştur
        UserResponse authorResponse = new UserResponse(comment.getAuthor().getId(), comment.getAuthor().getUserName());


        // TODO CommentResponse oluştur ve return et
        CommentResponse commentResponse = new CommentResponse(comment.getId(), comment.getContent(), comment.getCreatedAt(), authorResponse);

        return  commentResponse;
    }
}

package com.workintech.twitterApi.service;
import com.workintech.twitterApi.entity.Comment;

public interface CommentService {

    Comment findById(Long id);

    Comment saveComment(Comment comment);

    Comment updateComment(Long id, Comment comment);

    void deleteComment(Long id);
}

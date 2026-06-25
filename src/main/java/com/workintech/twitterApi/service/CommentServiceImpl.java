package com.workintech.twitterApi.service;
import com.workintech.twitterApi.entity.Comment;
import com.workintech.twitterApi.exceptions.CommentException;
import com.workintech.twitterApi.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;



@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepo;

    @Override
    public Comment findById(Long id) {
        Optional<Comment> optionalComment = commentRepo.findById(id);

        return optionalComment.orElseThrow(()-> new CommentException("Comment not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = findById(id);
        existingComment.setContent(comment.getContent());
        return commentRepo.save(existingComment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment deleteComment = findById(id);
        commentRepo.delete(deleteComment);
    }
}

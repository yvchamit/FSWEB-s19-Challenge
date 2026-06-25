package com.workintech.twitterApi.controller;
import com.workintech.twitterApi.converter.CommentConverter;
import com.workintech.twitterApi.dto.CommentRequest;
import com.workintech.twitterApi.dto.CommentResponse;
import com.workintech.twitterApi.entity.Comment;
import com.workintech.twitterApi.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @PostMapping
    public CommentResponse saveComment(@Valid @RequestBody CommentRequest commentRequest){
        Comment commentWillBeSaved = commentService.saveComment(commentConverter.toEntity(commentRequest));

        return commentConverter.toResponse(commentWillBeSaved);
    }

    @PutMapping("/{updateCommentId}")
    public CommentResponse updateComment(@PathVariable Long updateCommentId,@Valid @RequestBody CommentRequest commentRequest){

        Comment commentWillBeUpdated = commentService.updateComment(updateCommentId, commentConverter.toEntity(commentRequest));

        return commentConverter.toResponse(commentWillBeUpdated);
    }

    @DeleteMapping("/{deleteCommentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long deleteCommentId){
        commentService.deleteComment(deleteCommentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}

package com.workintech.twitterApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequest {

    @NotNull(message = "Tweet Id cannot be null")
    private Long tweetId;

    @NotNull(message = "User Id cannot be null")
    private Long userId;

    @NotBlank(message = "Content cannot be empty")
    @Size(max = 280, message = "Comment cannot exceed 280 characters")
    private String commentContent;
}

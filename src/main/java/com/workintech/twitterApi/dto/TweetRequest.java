package com.workintech.twitterApi.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class TweetRequest {
    @NotBlank(message = "Content cannot be empty")
    @Size(max = 280, message = "Tweet cannot exceed 280 characters")
    private String content;

    @NotNull(message = "UserId cannot be null")
    private Long userId;
}

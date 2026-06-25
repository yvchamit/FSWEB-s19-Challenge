package com.workintech.twitterApi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LikeRequest {

    @NotNull(message = "Tweet Id cannot be null")
    private Long tweetId;

    @NotNull(message = "User Id cannot be null")
    private Long userId;
}

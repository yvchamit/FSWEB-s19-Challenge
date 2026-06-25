package com.workintech.twitterApi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RetweetRequest {

    @NotNull
    private Long tweetId;

    @NotNull
    private Long userId;
}

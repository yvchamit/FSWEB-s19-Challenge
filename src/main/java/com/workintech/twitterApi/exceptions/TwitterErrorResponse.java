package com.workintech.twitterApi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TwitterErrorResponse {

    String message;

    int status;

    LocalDateTime timestamp;
}

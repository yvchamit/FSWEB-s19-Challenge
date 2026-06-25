package com.workintech.twitterApi.exceptions;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LikeException extends RuntimeException {

    private final HttpStatus httpStatus;

    public LikeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

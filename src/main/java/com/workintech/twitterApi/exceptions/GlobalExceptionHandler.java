package com.workintech.twitterApi.exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(TweetException ex){
        log.error("Tweet error exception has occurred! Details: ", ex);

        TwitterErrorResponse errorResponse = new TwitterErrorResponse(ex.getMessage(),ex.getHttpStatus().value(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse,ex.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(UserException ex){
        log.error("User error exception has occurred! Details: ", ex);

        TwitterErrorResponse errorResponse = new TwitterErrorResponse(ex.getMessage(),ex.getHttpStatus().value(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse,ex.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(CommentException ex){
        log.error("Comment error exception has occurred! Details: ", ex);

        TwitterErrorResponse errorResponse = new TwitterErrorResponse(ex.getMessage(),ex.getHttpStatus().value(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse,ex.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(LikeException ex){
        log.error("Like error exception has occurred! Details: ", ex);

        TwitterErrorResponse errorResponse = new TwitterErrorResponse(ex.getMessage(),ex.getHttpStatus().value(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse,ex.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(Exception ex){
        TwitterErrorResponse errorResponse = new TwitterErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TwitterErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);

        String message = fieldError.getField() + ": " + fieldError.getDefaultMessage();

        TwitterErrorResponse errorResponse = new TwitterErrorResponse(
                message,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

package com.shemi.tennisplayerrest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PlayerExceptionHandler {
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Map<String,PlayerErrorResponse>> playerNotFoundHandler(PlayerNotFoundException e, HttpServletRequest request) {
        PlayerErrorResponse error = new PlayerErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI(),
                e.getMessage()
        );
        Map<String,PlayerErrorResponse> result = new HashMap<>();
        result.put("error", error);
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String,PlayerErrorResponse>> genericHandler ( Exception ex, HttpServletRequest req){
        PlayerErrorResponse error = new PlayerErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                req.getRequestURI(),
                ex.getMessage());
        Map<String,PlayerErrorResponse> result = new HashMap<>();
        result.put("error", error);
        return new ResponseEntity<> (result, HttpStatus.BAD_REQUEST);

    }
}

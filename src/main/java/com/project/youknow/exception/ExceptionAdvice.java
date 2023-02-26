package com.project.youknow.exception;

import com.google.gson.Gson;
import com.project.youknow.exception.custom.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.nio.channels.AcceptPendingException;
import java.util.HashMap;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private Gson gson = new Gson();

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> defaultException(Exception e) throws Exception {
        e.printStackTrace();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", e.getMessage());

        String result = gson.toJson(resultMap);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            UnsatisfiedServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> badRequestException(Exception e) throws Exception {
        e.printStackTrace();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", e.getMessage());

        String result = gson.toJson(resultMap);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler({AuthenticationEntryPointException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> accessDeniedException(Exception e) throws Exception {
        e.printStackTrace();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", e.getMessage());

        String result = gson.toJson(resultMap);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    @ExceptionHandler({ForbiddenException.class, AcceptPendingException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> forbiddenException(ForbiddenException e) throws Exception {
        e.printStackTrace();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", e.getMessage());

        String result = gson.toJson(resultMap);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

    @ExceptionHandler({
            UserNotFoundException.class,
            EntityNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> notFoundException(Exception e) throws Exception {
        e.printStackTrace();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", e.getMessage());

        String result = gson.toJson(resultMap);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @ExceptionHandler(DuplicatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> duplicatedException(DuplicatedException e) throws Exception {
        e.printStackTrace();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", e.getMessage());

        String result = gson.toJson(resultMap);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @ExceptionHandler(ApiOtherException.class)
    public ResponseEntity<String> apiOtherException(ApiOtherException e) throws Exception {
        e.printStackTrace();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", e.getMessage());

        String result = gson.toJson(resultMap);

        return ResponseEntity.status(700).body(result);
    }
}

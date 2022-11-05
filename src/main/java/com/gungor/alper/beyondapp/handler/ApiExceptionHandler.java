package com.gungor.alper.beyondapp.handler;

import com.gungor.alper.beyondapp.dto.BaseErrorOutputDTO;
import com.gungor.alper.beyondapp.exception.JwtTokenException;
import com.gungor.alper.beyondapp.exception.UserAlreadyExists;
import com.gungor.alper.beyondapp.exception.UserException;
import com.gungor.alper.beyondapp.exception.UserNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({UserAlreadyExists.class, UserNotExistsException.class})
    public ResponseEntity<Object> handleApiRequestException(UserException e, HttpServletRequest request) {
        HttpStatus responseStatus = HttpStatus.FORBIDDEN;
        BaseErrorOutputDTO baseOutputDTO = new BaseErrorOutputDTO(responseStatus.name().toLowerCase(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(responseStatus)
                .body(baseOutputDTO);
    }


//    @ExceptionHandler
//    public ResponseEntity<Object> handleApiRequestException(JwtTokenException e, HttpServletRequest request) {
//        HttpStatus responseStatus = HttpStatus.UNAUTHORIZED;
//        BaseErrorOutputDTO baseOutputDTO = new BaseErrorOutputDTO(responseStatus.name().toLowerCase(),e.getMessage(),request.getRequestURI());
//        return ResponseEntity.status(responseStatus)
//                .body(baseOutputDTO);
//    }


    @ExceptionHandler
    public ResponseEntity<Object> handleApiRequestException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        String mapAsString = validationErrors.keySet().stream()
                .map(key -> key + "=" + validationErrors.get(key))
                .collect(Collectors.joining(", ", "{", "}"));


        BaseErrorOutputDTO baseOutputDTO = new BaseErrorOutputDTO(responseStatus.name().toLowerCase(), mapAsString, request.getRequestURI());
        return ResponseEntity.status(responseStatus)
                .body(baseOutputDTO);
    }
}

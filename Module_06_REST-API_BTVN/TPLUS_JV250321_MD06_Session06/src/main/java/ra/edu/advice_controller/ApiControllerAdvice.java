package ra.edu.advice_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.edu.model.dto.response.ApiDataResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(error.getObjectName(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(new ApiDataResponse<> (
                true,
                "MethodArgumentNotValidException",
                null,
                errors.toString(),
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiDataResponse<Object>> handleNoSuchElementExceptions(NoSuchElementException ex) {
        return new ResponseEntity<>(new ApiDataResponse<> (
                true,
                "NoSuchElementException",
                null,
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiDataResponse<Object>> handleRuntimeExceptions(RuntimeException ex) {
        return new ResponseEntity<>(new ApiDataResponse<> (
                true,
                "RuntimeException",
                null,
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
}

package ra.edu.controller_advice;

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
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "MethodArgumentNotValidException",
                null,
                errors.toString(),
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiDataResponse<Object>> handleNotFound(NoSuchElementException ex) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "NoSuchElementException",
                null,
                ex.getLocalizedMessage(),
                HttpStatus.NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiDataResponse<Object>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Internal server error",
                null,
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiDataResponse<Object>> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "BadRequestException",
                null,
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
}

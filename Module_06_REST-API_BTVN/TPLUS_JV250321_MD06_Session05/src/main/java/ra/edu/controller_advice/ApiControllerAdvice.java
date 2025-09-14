package ra.edu.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.edu.model.response.ApiDataResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((err) -> errors.put(err.getField(), err.getDefaultMessage()));

        log.error("{} - {}", "Tham so khong hop le", errors);
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Method Argument Not Valid!",
                null,
                errors.toString(),
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiDataResponse<Object>> handleNotFound(NoSuchElementException ex) {
        log.warn("NoSuchElementException: {}", ex.getMessage());
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
        log.error("Unhandled exception: ", ex);
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Internal server error",
                null,
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

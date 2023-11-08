package com.proyectootp.exception;
import org.springframework.http.*;
// import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// import java.net.URI;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponseRecord> handleAllException(Exception ex, WebRequest req){
        CustomErrorResponseRecord errorResponse = new CustomErrorResponseRecord(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponseRecord> handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        CustomErrorResponseRecord errorResponse = new CustomErrorResponseRecord(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<CustomErrorResponseRecord> handleSQLException(SQLException ex, WebRequest req){
        CustomErrorResponseRecord errorResponse = new CustomErrorResponseRecord(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(","));

        CustomErrorResponseRecord errorResponse = new CustomErrorResponseRecord(LocalDateTime.now(), message, request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
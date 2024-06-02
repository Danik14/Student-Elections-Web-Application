package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.api.dto.ErrorDto;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.exception.InvalidRoleException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class, InvalidRoleException.class})
    public ResponseEntity<ErrorDto> handleIllegalArgument(HttpServletRequest request, IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto.builder()
                        .timestamp(new Date())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .path(request.getServletPath())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<?> handleNotFound(HttpServletRequest request, RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorDto.builder()
                        .timestamp(new Date())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .path(request.getServletPath())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({ BadCredentialsException.class })
    public ResponseEntity<?> handleBadCredentials(HttpServletRequest request, RuntimeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ErrorDto.builder()
                        .timestamp(new Date())
                        .status(HttpStatus.FORBIDDEN.value())
                        .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                        .path(request.getServletPath())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<ErrorDto> handleMethodNotAllowed(ServletException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ErrorDto.builder()
                        .timestamp(new Date())
                        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .error(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                        .path(request.getServletPath())
                        .message(e.getMessage())
                        .build());
    }

}
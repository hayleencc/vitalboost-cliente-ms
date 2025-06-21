package org.vb.exception;

import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.Instant;


@RestControllerAdvice
public class GlobalExceptionHandler {
        private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
            logger.warn("Entity not found: {}", ex.getMessage());
            ApiError error = new ApiError(
                    Instant.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    ex.getMessage(),
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(EmailAlreadyExistsException.class)
        public ResponseEntity<ApiError> handleEmailExists(EmailAlreadyExistsException ex, HttpServletRequest request) {
            logger.warn("Email conflict: {}", ex.getMessage());
            ApiError error = new ApiError(
                    Instant.now(),
                    HttpStatus.CONFLICT.value(),
                    "Conflict",
                    ex.getMessage(),
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        @ExceptionHandler({JsonPatchException.class, IOException.class})
        public ResponseEntity<ApiError> handlePatchErrors(Exception ex, HttpServletRequest request) {
            logger.error("JSON Patch error", ex);
            ApiError error = new ApiError(
                    Instant.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al aplicar el parche JSON: " + ex.getMessage(),
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
            logger.error("Data integrity violation", ex);
            String message = "Error de integridad de datos";

            if (ex.getRootCause() != null && ex.getRootCause().getMessage().contains("clientes_email_key")) {
                message = "Ya existe un cliente con ese email";
            }

            ApiError error = new ApiError(
                    Instant.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    message,
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiError> handleGeneralException(Exception ex, HttpServletRequest request) {
            logger.error("Unexpected error", ex);
            ApiError error = new ApiError(
                    Instant.now(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error",
                    "Ocurrió un error inesperado",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
}

package com.project.poster.shared.common.error;

import com.project.poster.shared.common.exception.TechnicalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

import static com.project.poster.shared.common.error.ErrorMessage.ERROR_UNKNOWN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ErrorResponse> handleTechnicalException(TechnicalException ex) {
        final ErrorResponse response = buildDefaultErrorResponse()
                .status(ex.getStatus().value())
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .detailedMessage(ex.getDetailedMessage())
                .violations(ex.getViolations())
                .build();
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleDefaultException(Exception ex) {
        final ErrorResponse response = buildDefaultErrorResponse()
                .status(INTERNAL_SERVER_ERROR.value())
                .message(ERROR_UNKNOWN.getExceptionMessage())
                .errorCode(ERROR_UNKNOWN.getExceptionCode())
                .detailedMessage(ExceptionUtils.getStackTrace(ex))
                .build();
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse.ErrorResponseBuilder buildDefaultErrorResponse() {
        return ErrorResponse.builder().timestamp(Instant.now().toString());
    }

}

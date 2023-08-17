package com.project.poster.shared.common.exception;

import com.project.poster.shared.common.error.ErrorMessage;
import lombok.Getter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.Collections.emptyList;

@Getter
public class TechnicalException extends RuntimeException {

    private final HttpStatus status;
    private final String message;
    private final String errorCode;
    private final String detailedMessage;
    private final List<ErrorMessage> violations;

    public TechnicalException(HttpStatus status, ErrorMessage errorMessage, Throwable e) {
        super(errorMessage.getExceptionCode(), e);
        this.status = status;
        this.message = errorMessage.getExceptionMessage();
        this.errorCode = errorMessage.getExceptionCode();
        this.detailedMessage = ExceptionUtils.getStackTrace(e);
        this.violations = emptyList();
    }

    public TechnicalException(HttpStatus status, ErrorMessage errorMessage) {
        super(errorMessage.getExceptionCode());
        this.status = status;
        this.message = errorMessage.getExceptionMessage();
        this.errorCode = errorMessage.getExceptionCode();
        this.detailedMessage = null;
        this.violations = emptyList();
    }

}

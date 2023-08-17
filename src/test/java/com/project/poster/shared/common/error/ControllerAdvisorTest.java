package com.project.poster.shared.common.error;

import com.project.poster.shared.common.exception.TechnicalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static com.project.poster.shared.common.error.ErrorMessage.ERROR_UNKNOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControllerAdvisorTest {

    private static final String ERROR_MESSAGE = "error";

    @InjectMocks
    private ControllerAdvisor controllerAdvisor;

    @Test
    void shouldHandleTechnicalException() {
        //given
        TechnicalException ex = mock(TechnicalException.class);
        when(ex.getMessage()).thenReturn(ERROR_MESSAGE);
        when(ex.getStatus()).thenReturn(HttpStatus.BAD_REQUEST);

        //when
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleTechnicalException(ex);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(response.getBody()).status());
        assertEquals(ERROR_MESSAGE, response.getBody().message());
        assertNotNull(response.getBody().timestamp());
    }

    @Test
    void shouldHandleDefaultException() {
        //given
        IllegalStateException ex = mock(IllegalStateException.class);

        //when
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleDefaultException(ex);

        //then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), Objects.requireNonNull(response.getBody()).status());
        assertEquals(ERROR_UNKNOWN.getExceptionCode(), response.getBody().errorCode());
        assertEquals(ERROR_UNKNOWN.getExceptionMessage(), response.getBody().message());
        assertNotNull(response.getBody().timestamp());
    }

}
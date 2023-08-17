package com.project.poster.shared.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    /**
     * Error unknown
     */
    ERROR_UNKNOWN("errors.unknown", "Unknown error occurred"),

    /**
     * Error in external API
     */
    ERROR_EXTERNAL_API("errors.external-api", "Unknown error occurred in external API"),

    /**
     * Error while processing request
     */
    ERROR_PROCESSING_REQUEST("errors.request-processing", "Error while processing request"),

    /**
     * Error while processing web client request
     */
    ERROR_WEB_CLIENT_REQUEST("error.web-client-request", "Error while processing web client request"),

    /**
     * Error JSON processing
     */
    ERROR_JSON_PROCESSING("errors.json-parsing", "Exception while processing json"),

    /**
     * Error writing object to JSON file
     */
    ERROR_WRITING_JSON_FILE("errors.json-writing", "Exception while writing JSON file"),

    /**
     * Error while creating new directory
     */
    ERROR_CREATING_DIRECTORY("errors.create-directory", "Exception while creating new directory");

    private final String exceptionCode;
    private final String exceptionMessage;
}

package com.project.poster.shared.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.project.poster.shared.common.exception.TechnicalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.project.poster.shared.common.error.ErrorMessage.ERROR_CREATING_DIRECTORY;
import static com.project.poster.shared.common.error.ErrorMessage.ERROR_WRITING_JSON_FILE;

@Slf4j
public final class FileUtils {
    private FileUtils() {
    }

    private static final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    public static <T> boolean writeObjectToJsonFile(File file, T object) {
        try {
            objectWriter.writeValue(file, object);
            return true;
        } catch (IOException e) {
            log.error(ERROR_WRITING_JSON_FILE.getExceptionMessage().formatted(file.getName()), e);
            throw new TechnicalException(HttpStatus.BAD_REQUEST, ERROR_WRITING_JSON_FILE, e);
        }
    }

    public static void createDirectories(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            log.error(ERROR_CREATING_DIRECTORY.getExceptionMessage().formatted(path.toAbsolutePath()), e);
            throw new TechnicalException(HttpStatus.BAD_REQUEST, ERROR_CREATING_DIRECTORY, e);
        }
    }
}

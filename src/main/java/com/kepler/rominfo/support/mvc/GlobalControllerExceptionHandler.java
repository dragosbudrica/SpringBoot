package com.kepler.rominfo.support.mvc;

import com.kepler.rominfo.domain.logic.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger("com.kepler");

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ErrorResponse> defaultErrorHandler(Exception e) throws Exception {
        logger.error("Internal error", e);

        if (findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            // Exception already handle by the framework, we let it go
            throw e;
        }

        String error = e.getMessage();
        return new ResponseEntity<>(new ErrorResponse(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
package indi.henry.weatherdemo.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The final exception handling above the controllers
 * 
 * @author Henry Hu
 */
@RestControllerAdvice
public class ExceptionHandlers {

    private static final Logger appLog = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception e, HttpServletRequest request) {
        appLog.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleAppException(RuntimeException e, HttpServletRequest request) {
        appLog.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
}

package ai.openfabric.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> globalExceptionHandler(Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

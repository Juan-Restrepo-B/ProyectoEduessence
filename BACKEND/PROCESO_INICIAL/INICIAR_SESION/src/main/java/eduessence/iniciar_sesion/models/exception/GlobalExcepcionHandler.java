package eduessence.iniciar_sesion.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcepcionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerArgumentException(IllegalArgumentException ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RequestException.class)
    public ResponseEntity<String> handlerRuntimeException(RuntimeException ex){
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_GATEWAY);
    }
}
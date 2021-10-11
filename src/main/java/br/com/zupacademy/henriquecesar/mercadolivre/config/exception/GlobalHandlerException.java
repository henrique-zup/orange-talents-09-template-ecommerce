package br.com.zupacademy.henriquecesar.mercadolivre.config.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalHandlerException {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        return buildValidationErrors(globalErrors, fieldErrors);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessageDTO> handleResponseStatusException(ResponseStatusException ex) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO();
        errorMessage.addError(ex.getReason());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }

    private ErrorMessageDTO buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        globalErrors.forEach(err -> errorMessageDTO.addError(err.getDefaultMessage()));
        
        fieldErrors.forEach(err -> {
            String errorMsg = err.getDefaultMessage();
            errorMessageDTO.addFieldError(err.getField(), errorMsg);
        });

        return errorMessageDTO;
    }

}
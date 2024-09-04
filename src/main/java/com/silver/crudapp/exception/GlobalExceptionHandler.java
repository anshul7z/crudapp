package com.silver.crudapp.exception;

import com.silver.crudapp.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchUserExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    //@ResponseBody
    public ErrorResponse handleNoSuchUserExistsException(NoSuchUserExistsException ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.NOT_FOUND.value(),"ex.getMessage()"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

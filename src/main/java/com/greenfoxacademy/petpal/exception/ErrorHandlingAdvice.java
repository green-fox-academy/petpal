package com.greenfoxacademy.petpal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandlingAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMsg missingParams(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return new ErrorMsg("error", "Missing parameter(s): " + errors);
  }

  @ExceptionHandler(EmailTakenException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  ErrorMsg usernameTaken(EmailTakenException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }

  @ExceptionHandler(AnimalIdNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMsg animalNotFound(EmailTakenException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMsg userIdNotFound(EmailTakenException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }

  @ExceptionHandler(UserIsNullException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMsg userIsNull(EmailTakenException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }

  @ExceptionHandler(AnimalAlreadyAdoptedException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  ErrorMsg animalAlreadyAdopted(AnimalAlreadyAdoptedException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }

}

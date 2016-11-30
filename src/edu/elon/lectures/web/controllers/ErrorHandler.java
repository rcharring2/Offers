package edu.elon.lectures.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(DataAccessException.class)
  public String handleDatabaseExceptin(DataAccessException ex){
	ex.printStackTrace();
	return "error";
  }
  
  @ExceptionHandler(AccessDeniedException.class)
  public String handleAccessExceptin(AccessDeniedException ex){
	return "denied";
  }
  
}

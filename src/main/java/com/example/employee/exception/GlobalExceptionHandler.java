package com.example.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(AgeShouldBeAbove18Exception.class)
	    public ResponseEntity<String> handleAgeException(AgeShouldBeAbove18Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(NoEmployeesAvailableException.class)
	    public ResponseEntity<String> handleNoEmployeesAvailableException(NoEmployeesAvailableException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	 
	 
	 @ExceptionHandler(NoEmployeeFoundByIdException.class)
	 public ResponseEntity<String> handleNoEmployeeFoundByIdException(NoEmployeeFoundByIdException ex) {
	     return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	 }
	 
	  @ExceptionHandler(LeaveAlreadyExistsException.class)
	    public ResponseEntity<String> handleLeaveAlreadyExistsException(LeaveAlreadyExistsException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // 409 Conflict fits well here
	    }

}

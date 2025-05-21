package com.example.employee.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
	  
	  @ExceptionHandler(NoLeavesAvailableException.class)
	  public ResponseEntity<String> handleNoLeavesAvailableException(NoLeavesAvailableException ex) {
	      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(LeaveNotFoundByIdException.class)
	  public ResponseEntity<String> handleLeaveNotFoundByIdException(LeaveNotFoundByIdException ex) {
	      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	  }
	  

	    @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
	        Map<String, Object> error = new HashMap<>();
	        error.put("timestamp", LocalDateTime.now());
	        error.put("status", HttpStatus.NOT_FOUND.value());
	        error.put("error", "Not Found");
	        error.put("message", ex.getMessage());

	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(QualificationNotAvailableException.class)
	    public ResponseEntity<Map<String, Object>> handleQualificationNotAvailable(QualificationNotAvailableException ex) {
	        Map<String, Object> error = new HashMap<>();
	        error.put("timestamp", LocalDateTime.now());
	        error.put("status", HttpStatus.NOT_FOUND.value());
	        error.put("error", "Qualifications Not Found");
	        error.put("message", ex.getMessage());

	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

}

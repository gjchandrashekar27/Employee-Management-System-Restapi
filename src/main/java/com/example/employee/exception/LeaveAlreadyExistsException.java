package com.example.employee.exception;

public class LeaveAlreadyExistsException extends RuntimeException {
	
	 public LeaveAlreadyExistsException(String message) {
	        super(message);
	    }

}

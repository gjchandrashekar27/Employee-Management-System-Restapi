package com.example.employee.exception;

public class LeaveNotFoundByIdException extends RuntimeException{
	
	 public LeaveNotFoundByIdException(String message) {
	        super(message);
	    }

}

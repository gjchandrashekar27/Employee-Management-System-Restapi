package com.example.employee.exception;

public class NoEmployeeFoundByIdException extends RuntimeException{
	
	 public NoEmployeeFoundByIdException(String message) {
	        super(message);
	    }

}

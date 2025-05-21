package com.example.employee.exception;

public class NoLeavesAvailableException extends RuntimeException {
	
	public NoLeavesAvailableException(String message) {
        super(message);
    }

}

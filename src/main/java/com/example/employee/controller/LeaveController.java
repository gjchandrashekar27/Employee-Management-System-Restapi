package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Leave;
import com.example.employee.service.LeaveService;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController  {
	
	 @Autowired
	 LeaveService leaveService;
	 
	// Create leave
	    @PostMapping
	    public ResponseEntity<Leave> createLeave(@RequestBody Leave leave) {
	        Leave savedLeave = leaveService.createLeave(leave);
	        return new ResponseEntity<>(savedLeave, HttpStatus.CREATED);
	    }


}

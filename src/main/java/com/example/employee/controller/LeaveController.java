package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	    
	    @GetMapping
	    public ResponseEntity<List<Leave>> getAllLeaves() {
	        List<Leave> leaves = leaveService.getAllLeaves();
	        return new ResponseEntity<>(leaves, HttpStatus.OK);
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Leave> getLeaveById(@PathVariable Long id) {
	        Leave leave = leaveService.getLeaveById(id);
	        return new ResponseEntity<>(leave, HttpStatus.OK);
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Leave> updateLeave(@PathVariable Long id, @RequestBody Leave leave) {
	        Leave updatedLeave = leaveService.updateLeave(id, leave);
	        return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteLeave(@PathVariable Long id) {
	        leaveService.deleteLeaveById(id);
	        return ResponseEntity.ok("Leave with ID " + id + " has been deleted successfully.");
	    }



}

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

import com.example.employee.model.Qualification;
import com.example.employee.service.QualificationService;

@RestController
@RequestMapping("/api/qualifications")
public class QualificationController {
	
	@Autowired
	QualificationService qualificationService;
	
	// Create a new qualification for an employee
    @PostMapping("/{empId}")
    public ResponseEntity<Qualification> createQualification(@PathVariable Long empId,
                                                              @RequestBody Qualification qualification) {
        Qualification savedQualification = qualificationService.addQualification(qualification, empId);
        return new ResponseEntity<>(savedQualification, HttpStatus.CREATED);
    }
    
    // Get all qualifications
    @GetMapping
    public ResponseEntity<List<Qualification>> getAllQualifications() {
        List<Qualification> qualifications = qualificationService.getAllQualifications();
        return new ResponseEntity<>(qualifications, HttpStatus.OK);
    }
    
 // Get qualifications by employee ID
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<Qualification>> getQualificationsByEmployeeId(@PathVariable Long empId) {
        List<Qualification> qualifications = qualificationService.getQualificationsByEmployeeId(empId);
        return new ResponseEntity<>(qualifications, HttpStatus.OK);
    }
    
 // Delete a qualification by ID
    @DeleteMapping("/{qualId}")
    public ResponseEntity<Void> deleteQualification(@PathVariable Long qualId) {
        qualificationService.deleteQualification(qualId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
 // Update a qualification by ID
    @PutMapping("/{qualId}")
    public ResponseEntity<Qualification> updateQualification(@PathVariable Long qualId,
                                                              @RequestBody Qualification updatedQualification) {
        Qualification qualification = qualificationService.updateQualification(qualId, updatedQualification);
        return new ResponseEntity<>(qualification, HttpStatus.OK);
    }


}

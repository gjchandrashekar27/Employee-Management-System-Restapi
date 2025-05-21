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

import com.example.employee.model.Payroll;
import com.example.employee.service.PayrollService;

@RestController
@RequestMapping("/api/payrolls")
public class PayrollController {
	
	@Autowired
	PayrollService payrollService;
	
	@PostMapping("/{empId}/{jobId}/{salaryId}")
    public ResponseEntity<Payroll> createPayroll(@PathVariable Long empId,
                                                 @PathVariable Long jobId,
                                                 @PathVariable Long salaryId,
                                                 @RequestBody Payroll payroll) {
        Payroll savedPayroll = payrollService.addPayroll(payroll, empId, jobId, salaryId);
        return new ResponseEntity<>(savedPayroll, HttpStatus.CREATED);
    }
	
	@GetMapping
    public ResponseEntity<List<Payroll>> getAllPayrolls() {
        return new ResponseEntity<>(payrollService.getAllPayrolls(), HttpStatus.OK);
    }
	
	 @GetMapping("/{payrollId}")
	    public ResponseEntity<Payroll> getPayrollById(@PathVariable Long payrollId) {
	        Payroll payroll = payrollService.getPayrollById(payrollId);
	        return new ResponseEntity<>(payroll, HttpStatus.OK);
	    }
	
	 @PutMapping("/{payrollId}")
	    public ResponseEntity<Payroll> updatePayroll(@PathVariable Long payrollId,
	                                                 @RequestBody Payroll updatedPayroll) {
	        Payroll payroll = payrollService.updatePayroll(payrollId, updatedPayroll);
	        return new ResponseEntity<>(payroll, HttpStatus.OK);
	    }
	 
	 @DeleteMapping("/{payrollId}")
	    public ResponseEntity<Void> deletePayroll(@PathVariable Long payrollId) {
	        payrollService.deletePayroll(payrollId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	
	

}

package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.exception.QualificationNotAvailableException;
import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.model.Qualification;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.QualificationRepository;

@Service
public class QualificationServiceImpl  implements QualificationService{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	QualificationRepository qualificationRepository;

	@Override
	public Qualification addQualification(Qualification qualification, Long empId) {
		
		// Check if employee exists
	    Employee employee = employeeRepository.findById(empId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));

	    // Set the employee to qualification
	    qualification.setEmployee(employee);

	    // Save and return the qualification
	    return qualificationRepository.save(qualification);
		
	}

	@Override
	public List<Qualification> getAllQualifications() {
		List<Qualification> qualifications = qualificationRepository.findAll();
	    if (qualifications.isEmpty()) {
	        throw new QualificationNotAvailableException("No qualifications available");
	    }
	    return qualifications;
		
	}

	@Override
	public List<Qualification> getQualificationsByEmployeeId(Long empId) {
		// Check employee exists
	    Employee employee = employeeRepository.findById(empId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));

	    // Fetch qualifications by employee
	    List<Qualification> qualifications = qualificationRepository.findByEmployee(employee);

	    // Throw exception if empty
	    if (qualifications.isEmpty()) {
	        throw new QualificationNotAvailableException("No qualifications found for employee with ID: " + empId);
	    }

	    return qualifications;
	}

	@Override
	public void deleteQualification(Long qualId) {
		 Qualification qualification = qualificationRepository.findById(qualId)
		            .orElseThrow(() -> new ResourceNotFoundException("Qualification not found with ID: " + qualId));
		    
		    qualificationRepository.delete(qualification);
		
	}

	@Override
	public Qualification updateQualification(Long qualId, Qualification updatedQualification) {
		 Qualification existingQualification = qualificationRepository.findById(qualId)
		            .orElseThrow(() -> new ResourceNotFoundException("Qualification not found with ID: " + qualId));

		    existingQualification.setPosition(updatedQualification.getPosition());
		    existingQualification.setRequirements(updatedQualification.getRequirements());
		    existingQualification.setDateIn(updatedQualification.getDateIn());

		    return qualificationRepository.save(existingQualification);
	}

}

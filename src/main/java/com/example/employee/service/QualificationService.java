package com.example.employee.service;

import java.util.List;

import com.example.employee.model.Qualification;

public interface QualificationService {

	Qualification addQualification(Qualification qualification, Long empId);

	List<Qualification> getAllQualifications();

	List<Qualification> getQualificationsByEmployeeId(Long empId);

	void deleteQualification(Long qualId);

	Qualification updateQualification(Long qualId, Qualification updatedQualification);

}

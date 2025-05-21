package com.example.employee.service;

import java.util.List;

import com.example.employee.model.Leave;

public interface LeaveService {

	Leave createLeave(Leave leave);

	List<Leave> getAllLeaves();
	
	Leave getLeaveById(Long id);

	Leave updateLeave(Long id, Leave leave);

	void deleteLeaveById(Long id);

	

}

package com.example.employee.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.employee.common.StandardResponse;
import com.example.employee.model.VO.EmployeeVO;

@Transactional
@Service
public interface EmployeeInterfaceService {

	public StandardResponse addEmployees(EmployeeVO addreferpatient);
}

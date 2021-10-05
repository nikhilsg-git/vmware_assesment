package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.common.StandardResponse;
import com.example.employee.common.StatusResponse;
import com.example.employee.model.VO.EmployeeVO;
import com.example.employee.service.EmployeeService;

 
@CrossOrigin(origins = "http://localhost:3000")
@RestController
//@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@PostMapping(value="")
	public StandardResponse addEmployees(@RequestBody EmployeeVO addreferpatient){
		try {
		return employeeService.addEmployees(addreferpatient);
	}
	catch(Exception e) {
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		 standardResponse.setStatus(responseType);
			standardResponse.setMessage("Employee already added or failed to add Employee");
			return standardResponse;
	}
	}
 
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public StandardResponse getAllEmployees() {
		try {
        return employeeService.getAllEmployees();
	}
	catch(Exception e) {
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		 standardResponse.setStatus(responseType);
			standardResponse.setMessage("No information found");
			return standardResponse;
	}
    }
	
	
	@GetMapping(value = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StandardResponse getPatient(@PathVariable("empId") Long employeeId) {
		try {
        return employeeService.getEmployee(employeeId);
	}
	catch(Exception e) {
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		 standardResponse.setStatus(responseType);
			standardResponse.setMessage("No information found");
			return standardResponse;
	}
    }
	
	
	@GetMapping(value = "/{empId}/reportees", produces = MediaType.APPLICATION_JSON_VALUE)
    public StandardResponse getReportees(@PathVariable("empId") Long employeeId) {
		try {
        return employeeService.getReportees(employeeId);
		}
	catch(Exception e) {
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		 standardResponse.setStatus(responseType);
			standardResponse.setMessage("No information found");
			return standardResponse;
	}
    }
	
	@GetMapping(value = "/{empId}/managers", produces = MediaType.APPLICATION_JSON_VALUE)
    public StandardResponse getManagers(@PathVariable("empId") Long employeeId) {
		try {
        return employeeService.getManagers(employeeId);
		}
		catch(Exception e) {
			StatusResponse responseType = StatusResponse.ERROR;
			StandardResponse standardResponse = new StandardResponse();
			 standardResponse.setStatus(responseType);
				standardResponse.setMessage("No information found");
				return standardResponse;
		}
    }
	
	
	@GetMapping(value = "/supervisor/{empId1}/{empId2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StandardResponse getCommonSupervisor(@PathVariable("empId1") Long empId1,@PathVariable("empId2") Long empId2) {
		try {
        return employeeService.getCommonSupervisor(empId1,empId2);
		}
		catch(Exception e) {
			StatusResponse responseType = StatusResponse.ERROR;
			StandardResponse standardResponse = new StandardResponse();
			 standardResponse.setStatus(responseType);
				standardResponse.setMessage("No information found");
				return standardResponse;
		}
    }
	
	@PutMapping(value="/{empId}")
	public StandardResponse updateEmployees(@PathVariable("empId") Long empId,@RequestBody EmployeeVO updateEmployee){
		try {
		return employeeService.updateEmployees(empId,updateEmployee);
	}
	catch(Exception e) {
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		 standardResponse.setStatus(responseType);
			standardResponse.setMessage("No information found");
			return standardResponse;
	}
	}
	
}

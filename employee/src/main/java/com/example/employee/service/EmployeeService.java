package com.example.employee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.employee.common.EmployeeConstants;
import com.example.employee.common.StandardResponse;
import com.example.employee.common.StatusResponse;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.ReportingInformation;
import com.example.employee.dto.ReportingManagers;
import com.example.employee.model.Employee;
import com.example.employee.model.VO.EmployeeVO;
import com.example.employee.model.VO.ReporteesAndManagers;
import com.example.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Service
@Qualifier("EmployeeService")
public class EmployeeService implements EmployeeInterfaceService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public StandardResponse addEmployees(EmployeeVO employeeVO) {
		String message="";
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		
		
		Employee employeeModel = new Employee();
		if(null!=employeeVO.getFirstName() && null!=employeeVO.getLastName() && null!=employeeVO.getDesignation()
				&& null!=employeeVO.getLevel() && null!= employeeVO.getReportsTo() && employeeVO.getLevel() >= employeeVO.getReportsTo()) {
			EmployeeVO employeeVoexisting = employeeRepository.findByLevelAndReportsToAndEmail(employeeVO.getLevel(), employeeVO.getReportsTo(), employeeVO.getEmail());
			if(employeeVoexisting==null && (employeeVO.getReportsTo()!=employeeVO.getLevel() || (employeeVO.getReportsTo()==1 && employeeVO.getLevel()==1))) {
			employeeModel.setFirstName(employeeVO.getFirstName());
			employeeModel.setLastName(employeeVO.getLastName());
			employeeModel.setEmail(employeeVO.getEmail());
			employeeModel.setDesignation(employeeVO.getDesignation());
			employeeModel.setLevel(employeeVO.getLevel());
			employeeModel.setReportsTo(employeeVO.getReportsTo());
			Employee employeeSave;
			employeeSave=employeeRepository.save(employeeModel);
			if(employeeSave.getId()!=null) {
				message = EmployeeConstants.ADDEMPLOYEE_SUCCESS_MSG;
				responseType = StatusResponse.SUCCESS;
				responseMap.put("patientRef", employeeSave);
				standardResponse.setData(responseMap);
			}
		 
			}
			
			else {
				return new StandardResponse(StatusResponse.FAILED, "Only the CEO can report to himself --> with position number 1 or The Employee already exists");
			}
		}
		  else {
		    	return new StandardResponse(StatusResponse.FAILED, "One or More field missing--"
		    			+"firstName:String,--"
			    		+"lastName:String,--"
			    		+"email:String--,"
			    		+"designation:String,--"
			    		+"Level:Number,--"
			    		+"reportsTo:Number--"
			    		+"==========>"
			    		+ "==>Or Following conditions not met----->"
			    		+"Level of employee id should not be less than repoting employee id"); 
		    	
		  }
		
		standardResponse.setStatus(responseType);
		standardResponse.setMessage(message);
		return standardResponse;
		
	}

	public StandardResponse getEmployee(Long employeeId) {
//		EmployeeDto
		String message="";
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		 Optional<Employee> employeeDetails = employeeRepository.findById(employeeId);
		 Optional<Employee> employeeReports = employeeRepository.findById(employeeDetails.get().getReportsTo()); 
		 
		 
		 EmployeeDto employeeDto = new EmployeeDto();
		 ReportingInformation reportingInformationDto = new ReportingInformation();
		 
		 if (!employeeDetails.isPresent() || !employeeDetails.isPresent()) {
	        
			 return new StandardResponse(StatusResponse.FAILED, "Employee information not found for the given employeeId");
	        }
		 else {
			 employeeDto.setId(employeeDetails.get().getId());
			 employeeDto.setDesignation(employeeDetails.get().getDesignation());
			 employeeDto.setLevel(employeeDetails.get().getLevel());
			 employeeDto.setFirstName(employeeDetails.get().getFirstName());
			 employeeDto.setLastName(employeeDetails.get().getLastName());
			 employeeDto.setEmail(employeeDetails.get().getEmail());
			 reportingInformationDto.setDesignation(employeeReports.get().getDesignation());
			 reportingInformationDto.setFirstName(employeeReports.get().getFirstName());
			 reportingInformationDto.setLastName(employeeReports.get().getLastName());
			 reportingInformationDto.setLevel(employeeReports.get().getLevel());
			 employeeDto.setReportsTo(reportingInformationDto);
			 message = EmployeeConstants.GETEMPLOYEE_SUCCESS_MSG;
			 responseType = StatusResponse.SUCCESS;
			 responseMap.put("getEmployee", employeeDto);
			 standardResponse.setData(responseMap);
		 }
		    standardResponse.setStatus(responseType);
			standardResponse.setMessage(message);
			return standardResponse;
	}


	public StandardResponse getReportees(Long employeeId) {
		String message="";
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		List<Employee> employeeVO =new ArrayList<>();
		Employee employeeDetails;
		
 
		HashMap<Long,Long> allRecords = new HashMap<>();
		List<Employee> empAll = employeeRepository.findAll();
		List<Long> reportees ;

		for ( Employee e: empAll) {
			allRecords.put(e.getId(), e.getReportsTo());
		}
		
		reportees=ReporteesAndManagers.findEmployees(allRecords,employeeId);
		
		for(Long r:reportees) {
			employeeDetails=employeeRepository.getById(r);
			employeeVO.add(employeeDetails);
		}
		
		 if (employeeVO.isEmpty()) {
		        
			 return new StandardResponse(StatusResponse.FAILED, "No Reportees found under employeeId:"+employeeId);
	        }
		 else {
			
			System.out.println("MY reportees"+employeeVO);
			 message = EmployeeConstants.GETREPORTEE_SUCCESS_MSG;
			 responseType = StatusResponse.SUCCESS;
			 responseMap.put("reporteesDirectAndIndirect", employeeVO);
			 standardResponse.setData(responseMap);
		 }
		 
		    standardResponse.setStatus(responseType);
			standardResponse.setMessage(message);
			return standardResponse;
	}
	
	
	public StandardResponse getManagers(Long employeeId) {
		String message="";
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		List<Employee> employeeVO =new ArrayList<>();
		ReportingManagers managers; 
		List<ReportingManagers> rm = new ArrayList<>();
		List<Long> allManagers = new ArrayList<>();
		Employee employeeDetails;
		HashMap<Long,Long> allRecords = new HashMap<>();
		List<Employee> empAll = employeeRepository.findAll();
		List<Long> reportees;
		try {
			
		employeeDetails=employeeRepository.getById(employeeId);
	 
		if(employeeId==1) {
			 message = EmployeeConstants.GETMANAGERS_SUCCESS_MSG;
			 responseType = StatusResponse.SUCCESS;
			 responseMap.put("managersDirectAndIndirect", employeeDetails);
			 standardResponse.setData(responseMap);
		}
		else if(employeeDetails.getId()!=null){
			
		for ( Employee e: empAll) {
			managers = new ReportingManagers();
			managers.setId(e.getId());		
			managers.setLevel(e.getLevel());
			managers.setReportsTo(e.getReportsTo());
			rm.add(managers);
			allRecords.put(e.getId(),e.getReportsTo());
		}
		
		System.out.println("Managers===>"+rm);
//		Long minLevel = employeeRepository.findMaxLevel()
		Long minLevel = employeeDetails.getLevel();

		while(minLevel != 1)
		{
		
			for(int i=0;i<rm.size();i++) {
			
				if(rm.get(i).getId().equals(employeeId)) {
					
					allManagers.add(rm.get(i).getReportsTo());
					employeeId = rm.get(i).getReportsTo();
					minLevel--;
					
				}
			}
		}
		
		for(Long r:allManagers) {
			employeeDetails=employeeRepository.getById(r);
			employeeVO.add(employeeDetails);
		}
		 if (employeeVO.isEmpty()) {
		        
			 return new StandardResponse(StatusResponse.FAILED, "No Reportees found under employeeId:"+employeeId);
	        }
		 else {
		 message = EmployeeConstants.GETMANAGERS_SUCCESS_MSG;
		 responseType = StatusResponse.SUCCESS;
		 responseMap.put("managersDirectAndIndirect", employeeVO);
		 standardResponse.setData(responseMap);
		 }
		
	}	 
 
	}
		catch(Exception e) {
			return new StandardResponse(StatusResponse.FAILED, "Employee Not found:"+employeeId);
		}
		 standardResponse.setStatus(responseType);
			standardResponse.setMessage(message);
			return standardResponse;
	}

	
	
	public StandardResponse getCommonSupervisor(Long empId1, Long empId2) {
		
		String message="";
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		Employee emp1,emp2;
		Employee supervisor = new Employee();;
		EmployeeVO commonSupervisor = new EmployeeVO();
	
		
		try {
		  emp1 = employeeRepository.getById(empId1);
		  emp2 = employeeRepository.getById(empId2);
		
		  if(emp1.getId() == 1 || emp2.getId() == 1) {
			    supervisor = emp1.getId()==1?emp1:emp2;
			  
		  }
		  else if(emp1.getId() == emp2.getId())
		  {
			    supervisor = employeeRepository.getById(emp1.getReportsTo());
			
		  }
		  else if((emp1.getLevel() > emp2.getLevel()) || (emp2.getLevel() > emp1.getLevel())){
 
				Employee submanager = emp1.getLevel() < emp1.getLevel() ? emp1 : emp2;
				Long level;
				while(emp1.getReportsTo()!=emp2.getReportsTo()) {
					 if(emp1.getLevel() < emp2.getLevel() ) {
						 level=emp2.getLevel();
						 while(level!=emp1.getLevel()) {
							 emp2=employeeRepository.getById(emp2.getReportsTo());
							 level--;	 
						 }
					 }
					 else if(emp1.getLevel() > emp2.getLevel() ){
						 level=emp1.getLevel();
						 while(level!=emp2.getLevel()) {
							 emp1=employeeRepository.getById(emp1.getReportsTo());
							 level--;	
						 
					     }
					
				     }
					 else {
						 if(emp1.getReportsTo()==emp2.getReportsTo())
							 supervisor= employeeRepository.getById(emp1.getReportsTo());
						 else
							 continue;
					 }
//				  supervisor = employeeRepository.getById(manager.getReportsTo());
					 
			 }	
				 supervisor= employeeRepository.getById(emp1.getReportsTo());
		  }
		  else {
			  Long level = emp1.getLevel();
			  
			  while(level!=1) {
				  if(emp1.getReportsTo()==emp2.getReportsTo())
				  {
					 supervisor = employeeRepository.getById(emp1.getReportsTo());
					 level=(long) 1;
							 
				  }
				  else {
					  emp1 = employeeRepository.getById(emp1.getReportsTo());
					  emp2 = employeeRepository.getById(emp2.getReportsTo());
					  level--;
				  }
				  
			  }
		  
		  }
		  
		  
		  commonSupervisor.setEmployeeId(supervisor.getId());
		  commonSupervisor.setFirstName(supervisor.getFirstName());
		  commonSupervisor.setLastName(supervisor.getLastName());
		  commonSupervisor.setDesignation(supervisor.getDesignation());
		  commonSupervisor.setLevel(supervisor.getLevel());
		  commonSupervisor.setEmail(supervisor.getEmail());
		  commonSupervisor.setReportsTo(supervisor.getReportsTo()); 
		  
		  	message = EmployeeConstants.GETREPORTEE_SUCCESS_MSG;
			 responseType = StatusResponse.SUCCESS;
			 responseMap.put("commonSupervisor", commonSupervisor);
			 standardResponse.setData(responseMap);
		  
		  
		}
		catch(Exception e) {
			return new StandardResponse(StatusResponse.FAILED, "Failed to retrieve information");
		}
		
		standardResponse.setStatus(responseType);
		standardResponse.setMessage(message);
		return standardResponse;
	}

	public StandardResponse updateEmployees(Long empId, EmployeeVO updateEmployee) {
		
		String message="";
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		Employee employeeSave = employeeRepository.getById(empId);
		
		System.out.println(employeeRepository.findById(empId));
		System.out.println(employeeRepository.findByEmaild(updateEmployee.getEmail()));
		
		try {
		if(null!=updateEmployee.getEmployeeId() && null!=updateEmployee.getFirstName() && null!=updateEmployee.getLastName() && null!=updateEmployee.getEmail() && null!=updateEmployee.getDesignation() && 
				null!=updateEmployee.getReportsTo() && null!=updateEmployee.getLevel())
			return new StandardResponse(StatusResponse.FAILED, "No Employee Information found to be updated");
		
		
		if(null!=updateEmployee.getEmployeeId() || null==employeeRepository.findById(empId)) 
			return new StandardResponse(StatusResponse.FAILED, "No Employee Information found");
		else
			employeeSave.setId(empId);
		
		if(null!=updateEmployee.getEmail() && null==employeeRepository.findByEmaild(updateEmployee.getEmail())) {
			employeeSave.setEmail(updateEmployee.getEmail());
		}
		else {
			return new StandardResponse(StatusResponse.FAILED, "EmailID already exist or updating with same email address is not allowed");
		}
		if(null!=updateEmployee.getFirstName())
			employeeSave.setFirstName(updateEmployee.getFirstName());
		if(null!=updateEmployee.getLastName())
			employeeSave.setLastName(updateEmployee.getLastName());
		if( null!=updateEmployee.getDesignation())
			employeeSave.setDesignation(updateEmployee.getDesignation());
		
		if(null!=updateEmployee.getReportsTo()) {
			
			System.out.println(employeeRepository.findByReportsTo(empId).isEmpty());
		if(!employeeRepository.findByReportsTo(empId).isEmpty())
			return new StandardResponse(StatusResponse.FAILED, "Employee has hireachial reportees , cannot update information ==> Dissolve reportees and then update ");
		else
			employeeSave.setReportsTo(updateEmployee.getReportsTo());
		}
		employeeRepository.save(employeeSave);
		message = EmployeeConstants.GETREPORTEE_SUCCESS_MSG;
		 responseType = StatusResponse.SUCCESS;
		 responseMap.put("updateEmployee", employeeSave);
		 standardResponse.setData(responseMap);
		}
		catch(Exception e)  {
			return new StandardResponse(StatusResponse.FAILED, "Failed to retrieve information"); 
		}
		
		standardResponse.setStatus(responseType);
		standardResponse.setMessage(message);
		return standardResponse;
		 
	}

	public StandardResponse getAllEmployees() {
		String message="";
		StatusResponse responseType = StatusResponse.ERROR;
		StandardResponse standardResponse = new StandardResponse();
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		 List<Employee> employeeDetails = employeeRepository.findAll();
	 
		 
		 EmployeeDto employeeDto = new EmployeeDto();
		 ReportingInformation reportingInformationDto = new ReportingInformation();
		 
		 if (employeeDetails.isEmpty()) {
	        
			 return new StandardResponse(StatusResponse.FAILED, "No Employee Found");
	        }
		 else {
//			 employeeDto.setId(employeeDetails.get().getId());
//			 employeeDto.setDesignation(employeeDetails.get().getDesignation());
//			 employeeDto.setLevel(employeeDetails.get().getLevel());
//			 employeeDto.setFirstName(employeeDetails.get().getFirstName());
//			 employeeDto.setLastName(employeeDetails.get().getLastName());
//			 employeeDto.setEmail(employeeDetails.get().getEmail());
//			 reportingInformationDto.setDesignation(employeeReports.get().getDesignation());
//			 reportingInformationDto.setFirstName(employeeReports.get().getFirstName());
//			 reportingInformationDto.setLastName(employeeReports.get().getLastName());
//			 reportingInformationDto.setLevel(employeeReports.get().getLevel());
//			 employeeDto.setReportsTo(reportingInformationDto);
			 message = EmployeeConstants.GETEMPLOYEE_SUCCESS_MSG;
			 responseType = StatusResponse.SUCCESS;
			 responseMap.put("getEmployee", employeeDetails);
			 standardResponse.setData(responseMap);
		 }
		    standardResponse.setStatus(responseType);
			standardResponse.setMessage(message);
			return standardResponse;
	}

}

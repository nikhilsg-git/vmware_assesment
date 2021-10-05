package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.employee.model.Employee;
import com.example.employee.model.VO.EmployeeVO;


public interface EmployeeRepository extends JpaRepository<Employee, Long>  {
	
	Employee findByLevel(Long id);
	
	@Query(
			  value = "SELECT * FROM EMPLOYEE AS E WHERE E.LEVEL = ?1 AND E.REPORTS_TO = ?2 AND E.EMAIL = ?3", 
			  nativeQuery = true)
	EmployeeVO findByLevelAndReportsToAndEmail(Long level,Long reportId, String email);
	

	@Query(
			  value = "SELECT * FROM EMPLOYEE AS E WHERE E.LEVEL > "
			  		+ "(SELECT S.LEVEL FROM EMPLOYEE AS S WHERE S.ID = ?1)", 
			  nativeQuery = true)
	List<EmployeeVO> findAllReportees(Long employeeId);
	
	@Query(
			  value = "SELECT * FROM EMPLOYEE AS E WHERE E.LEVEL < "
			  		+ "(SELECT S.LEVEL FROM EMPLOYEE AS S WHERE S.ID = ?1)", 
			  nativeQuery = true)
	List<EmployeeVO> findAllManagers(Long employeeId);

	@Query(
			  value = "SELECT * FROM EMPLOYEE AS E WHERE E.REPORTS_TO = ?1", 
			  nativeQuery = true)
	List<Employee> findByReportsTo(Long i);
	
 	
	@Query(
	  value = "SELECT E.EMPLOYEE,E.REPORTS_TO FROM EMPLOYEE AS E WHERE E.REPORTS_TO = ?1", 
	  nativeQuery = true)
	List<EmployeeVO> findByReporteesAndManagers();

	@Query(
			  value = "SELECT MAX(LEVEL) FROM EMPLOYEE", 
			  nativeQuery = true)
	Long findMaxLevel();
	
	@Query(
			  value = "SELECT * FROM EMPLOYEE E WHERE E.EMAIL = ?1", 
			  nativeQuery = true)
	Employee findByEmaild(String email);

//	@Query(
//			  value = "UPDATE E.EMPLOYEE,E.REPORTS_TO FROM EMPLOYEE AS E WHERE E.REPORTS_TO = ?1", 
//			  nativeQuery = true)
//	void updateEmployee(Long empId, Employee employeeSave);
}

package com.example.employee.model.VO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReporteesAndManagers {

	public static List<Long> findAllReportingEmployees(Long manager, Map<Long, List<Long>> managerToEmployeeMappings,
			Map<Long, List<Long>> result) {
 
		if (result.containsKey(manager)) {
 
			return result.get(manager);
		}
 
		List<Long> managerEmployees = managerToEmployeeMappings.get(manager);

		if (managerEmployees != null) {
 
			for (Long reportee : new ArrayList<>(managerEmployees)) {
 
				List<Long> employees = findAllReportingEmployees(reportee, managerToEmployeeMappings, result);
 
				if (employees != null) {
					managerEmployees.addAll(employees);
				}
			}
		}

		result.put(manager, managerEmployees);
		return managerEmployees;
	}

	public static List<Long> findEmployees(Map<Long, Long> employeeToManagerMappings, Long empId) {

		Map<Long, List<Long>> managerToEmployeeMappings = new HashMap<>();
		List<Long> reportees = new ArrayList<>();

		for (Map.Entry<Long, Long> entry : employeeToManagerMappings.entrySet()) {
			Long employee = entry.getKey();
			Long manager = entry.getValue();

			if (employee != manager) {
				managerToEmployeeMappings.putIfAbsent(manager, new ArrayList<>());
				managerToEmployeeMappings.get(manager).add(employee);
			}
		}

		Map<Long, List<Long>> result = new HashMap<>();

		for (Map.Entry<Long, Long> entry : employeeToManagerMappings.entrySet()) {
			findAllReportingEmployees(entry.getKey(), managerToEmployeeMappings, result);
		}

		for (Entry<Long, List<Long>> entry : result.entrySet()) {
			System.out.println(entry.getKey() + " —> " + entry.getValue());
			if(entry.getKey()==empId)
				reportees.addAll(entry.getValue());
		}
		
		System.out.println("reportees======>" + reportees);
		
		return reportees;
	}
	
	
	public static List<Long> findAllReportingManagers(Long manager, Map<Long, List<Long>> managerToEmployeeMappings,
			Map<Long, List<Long>> result) {
 
		if (result.containsKey(manager)) {
 
			return result.get(manager);
		}
 
		List<Long> managerEmployees = managerToEmployeeMappings.get(manager);

		if (managerEmployees != null) {
 
			for (Long reportee : new ArrayList<>(managerEmployees)) {
 
				List<Long> employees = findAllReportingEmployees(reportee, managerToEmployeeMappings, result);
 
				if (employees != null) {
					managerEmployees.addAll(employees);
				}
			}
		}

		result.put(manager, managerEmployees);
		return managerEmployees;
	}

	public static List<Long> findManagers(Map<Long, Long> employeeToManagerMappings, Long empId) {

		Map<Long, List<Long>> managerToEmployeeMappings = new HashMap<>();
		List<Long> reportees = new ArrayList<>();

		for (Map.Entry<Long, Long> entry : employeeToManagerMappings.entrySet()) {
			Long employee = entry.getKey();
			Long manager = entry.getValue();

			if (employee != manager) {
				managerToEmployeeMappings.putIfAbsent(manager, new ArrayList<>());
				managerToEmployeeMappings.get(manager).add(employee);
			}
		}

		Map<Long, List<Long>> result = new HashMap<>();

		for (Map.Entry<Long, Long> entry : employeeToManagerMappings.entrySet()) {
			findAllReportingEmployees(entry.getKey(), managerToEmployeeMappings, result);
		}

		for (Entry<Long, List<Long>> entry : result.entrySet()) {
			System.out.println(entry.getKey() + " —> " + entry.getValue());
			if(entry.getKey()==empId)
				reportees.addAll(entry.getValue());
		}
		
		System.out.println("reportees======>" + reportees);
		
		return reportees;
	}

}

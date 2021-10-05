package com.example.employee.dto;

import java.util.Optional;

import com.example.employee.common.StandardResponse;
import com.example.employee.model.Employee;
import com.example.employee.model.VO.EmployeeVO;

import lombok.Data;

@Data
public class EmployeeResponse extends BaseAPIResponse {

    private EmployeeDto data;

}

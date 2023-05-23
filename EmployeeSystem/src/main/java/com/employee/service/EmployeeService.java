package com.employee.service;

import java.util.List;

import com.employee.entities.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmployees();
	public Employee addEmployee(Employee employee);
	public Employee findEmployeeById(Integer id);
	public Employee updateEmployee(Integer id,Employee employee);
	public String deleteEmployee(Integer id);

}

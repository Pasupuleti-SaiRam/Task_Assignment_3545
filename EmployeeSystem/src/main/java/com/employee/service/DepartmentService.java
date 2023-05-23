package com.employee.service;

import java.util.List;

import com.employee.entities.Department;
import com.employee.entities.Employee;

public interface DepartmentService {
	
	public Department createDepartment(Department department);
	
	//public Department addEmployeeToDepartment(Integer id, Employee employee);
	
	public Department findDepartmentById(Integer id);	
	
	public List<Department> getDepartments();
	
	public String delateDepartment(Integer id);
	
	
	//public List<Employee> getAllEmployeesByDepartmentId();
	
	public Department updateDepartment(Integer id,Department department);

}

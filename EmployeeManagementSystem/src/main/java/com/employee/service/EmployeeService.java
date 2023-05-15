package com.employee.service;
import java.util.List;
import com.employee.entity.Employee;
public interface EmployeeService {
	
	public Employee saveEmployee(Employee employee);
	public List<Employee> getEmployees();
	public Employee getEmployeeById(Integer id);
	public Employee getEmployeeByName(String name);
	public String deleteEmployee(Integer id);
	public Employee updateEmployee(Integer id,Employee employee);

}

package com.employee.serviceImpl;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employee.entities.Department;
import com.employee.entities.Employee;
import com.employee.repositories.DepartmentRepository;
import com.employee.repositories.EmployeeRepository;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl  implements EmployeeService{
	@Autowired 
	private EmployeeRepository employeeRepo;
	@Autowired 
	private DepartmentRepository departmentRepository;
	@Override
	public List<Employee> getEmployees() {
		
		return employeeRepo.findAll();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		
	Integer departmentID  = employee.getDepartments().get(0).getId();
	Optional<Department> department = departmentRepository.findById(departmentID);
	
	if(department.isPresent()) {
	 employee.setDepartments(Collections.singletonList(department.get()));
	}
		return employeeRepo.save(employee);
		
	}

	@Override
	public Employee findEmployeeById(Integer id) {
		Optional<Employee> employee = employeeRepo.findById(id);
		if(employee.isPresent()) {
			return employee.get() ;
		}
		
		 throw new RuntimeException("id is not found");
	}

	@Override
	public Employee updateEmployee(Integer id, Employee employee) {
		Optional<Employee> existEmployee = employeeRepo.findById(id);
		Optional<Department> existDepartment = departmentRepository.findById(employee.getDepartments().get(0).getId());
		
		if(existEmployee.isPresent()) {
			existEmployee.get().setName(employee.getName());
			existEmployee.get().setGender(employee.getGender());
			existEmployee.get().setDesignation(employee.getDesignation());
			existEmployee.get().setDate(employee.getDate());
		if(existDepartment.isPresent())
			existEmployee.get().setDepartments(employee.getDepartments());
			return employeeRepo.save(existEmployee.get());
		}
		throw new RuntimeException("id is not found");
	}

	@Override
	public String deleteEmployee(Integer id) {
		Optional<Employee> employee = employeeRepo.findById(id);
		if(employee.isPresent()) {
			employeeRepo.delete(employee.get());
			return "Record Deleted";
		}
		throw new RuntimeException("id is not found");
	}

}

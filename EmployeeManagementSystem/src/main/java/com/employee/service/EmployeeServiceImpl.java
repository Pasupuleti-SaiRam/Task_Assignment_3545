package com.employee.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.exceptions.EmployeeNotFoundException;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl  implements EmployeeService{
	@Autowired
	private EmployeeRepository repo;
	@Autowired
	private DepartmentRepository dRepo;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		return repo.findAll();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return repo.findById(id).orElseThrow(
				()-> new EmployeeNotFoundException("Employee is not found with this id ::"+id));
				
	}

	@Override
	public Employee getEmployeeByName(String name) {
		return repo.findByName(name).orElseThrow(
				()-> new EmployeeNotFoundException("Employee not found with this name ::"+name));
		 
	}

	@Override
	public String deleteEmployee(Integer id) {
		repo.findById(id).orElseThrow(
				()-> new EmployeeNotFoundException("Employee is not available with this id ::"+id));
		return "Employee Deleted";
		
		
	}
	public Employee updateEmployee(Integer id,Employee employee) {
		Optional<Department> department = dRepo.findById(id);
		Employee existingEmployee = repo.findById(id).orElseThrow(
				()->new EmployeeNotFoundException("Employee is not found with this id ::"+id));
		if(employee.getName() !=null) {
			existingEmployee.setName(employee.getName());
			if(employee.getEmail()!=null)
			existingEmployee.setEmail(employee.getEmail());
			if(employee.getPhoneNumber()!=null)
			existingEmployee.setPhoneNumber(employee.getPhoneNumber());
			
			existingEmployee.setSalary(employee.getSalary());
			
			department.get().setName(employee.getDepartment().name);
		}
			return repo.save(existingEmployee);
	}

}

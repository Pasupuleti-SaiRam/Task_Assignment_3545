package com.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entities.Employee;
import com.employee.serviceImpl.EmployeeServiceImpl;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeService;
	@GetMapping("/employee")
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Integer id) {
		 return employeeService.findEmployeeById(id);
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> addEmployeeToDept(@RequestBody Employee employee){
	System.out.println(" employee details "+employee);
		return new ResponseEntity<Employee>(employeeService.addEmployee(employee),HttpStatus.CREATED);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.updateEmployee(id, employee),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		
		return new  ResponseEntity<String>(employeeService.deleteEmployee(id),HttpStatus.OK);
	}

}

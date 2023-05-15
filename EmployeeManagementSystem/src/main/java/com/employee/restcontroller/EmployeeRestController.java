package com.employee.restcontroller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeServiceImpl;
@RequestMapping("/employee")
@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeServiceImpl service;
	@GetMapping("/")
	public List<Employee> getEmployees(){
		return service.getEmployees();
	}
	@PostMapping("/add")
	public ResponseEntity<Employee> saveEmaployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee> (service.saveEmployee(employee),HttpStatus.OK);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
		return new ResponseEntity<Employee>( service.getEmployeeById(id),HttpStatus.OK);
	}
	@GetMapping("/get/{name}")
	public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name) {
		 return new ResponseEntity<Employee> (service.getEmployeeByName(name),HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		service.deleteEmployee(id);
		String msg="Employee deleted";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee employee){
		return new ResponseEntity<Employee>(service.updateEmployee(id, employee),HttpStatus.OK);
	}

}

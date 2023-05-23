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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entities.Department;
import com.employee.serviceImpl.DepartmentServiceImpl;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentServiceImpl departmentService;
	@PostMapping("/")
	public ResponseEntity<Department> createDepartment(@RequestBody Department department){
		return new ResponseEntity<Department>(departmentService.createDepartment(department),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id){
		return new  ResponseEntity<Department>(departmentService.findDepartmentById(id),HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<Department>> getAllDepartmets(){
		return new ResponseEntity<List<Department>>(departmentService.getDepartments(),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Integer id){
		return new ResponseEntity<String>(departmentService.delateDepartment(id),HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Integer id,@RequestBody Department department){
		return new ResponseEntity<Department>(departmentService.updateDepartment(id, department),HttpStatus.OK);
		
	}
	
	

}

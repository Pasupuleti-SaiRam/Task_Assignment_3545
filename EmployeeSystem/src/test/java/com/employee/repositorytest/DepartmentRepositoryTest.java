package com.employee.repositorytest;


import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.employee.entities.Department;

import com.employee.repositories.DepartmentRepository;

@DataJpaTest
@TestMethodOrder(value = OrderAnnotation.class)
public class DepartmentRepositoryTest {
	
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	private Department department ;
	@BeforeEach
	public void setUp() {
		//initializing employee object
		 department = new Department();
		//department.setId(1);
		department.setName("Java Practice");
	}
	@Test
	@Order(1)
	@Rollback(value=false)
	public void testCreateDepartment() {
		
		Department savedDepartment = departmentRepository.save(department);
		System.out.println(department);
		Assertions.assertThat(savedDepartment.getId()).isGreaterThan(0);
	}
	@Test
	@Order(2)
	public void testFindDepartmentById() {
		
		 Department department = departmentRepository.findById(1).get();
		 System.out.println(department);
		 Assertions.assertThat(department.getId()).isEqualTo(1);
	}
	@Test
	@Order(3)
	public void testListOfDepartments() {
		List<Department> departments = departmentRepository.findAll();
		Assertions.assertThat(departments.size()).isGreaterThan(0);
	}
	@Test
	@Order(4)
	public void testUpdateDepartment() {
		 Department department = departmentRepository.findById(1).get();
		 department.setName("IT");
		 Department updatedDepartment = departmentRepository.save(department);
		Assertions.assertThat(updatedDepartment.getName()).isEqualTo("IT");
		
	}
	@Test
	@Order(5)
	public void testDelateDepartment() {
		
		Department  department  = departmentRepository.findById(1).orElseThrow(null);
		departmentRepository.delete(department);
		Optional<Department> deletedDepartment = departmentRepository.findById(1);
		Assertions.assertThat(deletedDepartment).isEmpty();
	}


}

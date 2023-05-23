package com.employee.repositorytest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;
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

import com.employee.entities.Employee;
import com.employee.repositories.EmployeeRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	private Employee employee;
	@BeforeEach
	public void setUp() {
		//initializing employee object
		employee= new Employee();
		employee.setName("Sairam");
		employee.setGender("Male");
		employee.setDesignation("Technical Associate");
		employee.setSalary(37000.00);
		employee.setDate(LocalDate.now());
	}
	@Test
	@Order(1)
	@Rollback(value=false)
	public void testSaveEmployee() {
		Employee savedEmployee = employeeRepository.save(employee);
		Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	@Test
	@Order(2)
	public void testGetEmployee() {
		 Employee employee= employeeRepository.findById(1).get();
		 Assertions.assertThat(employee.getId()).isEqualTo(1); 
	}
	@Test
	@Order(3)
	public void testGetListOfEmployees() {
		List<Employee> listOfemployees = employeeRepository.findAll();
		Assertions.assertThat(listOfemployees.size()).isGreaterThan(0);
	}
	@Test
	@Order(4)
	public void testUpdateEmployee() {
		 Employee employee = employeeRepository.findById(1).get();
		 employee.setName("Ram");
		Employee updatedEmployee = employeeRepository.save(employee);
		Assertions.assertThat(updatedEmployee.getName()).isEqualTo("Ram");
		
	}
	@Test
	@Order(5)
	public void testDelateEmployee() {
		
		Employee employee = employeeRepository.findById(1).orElseThrow(null);
		employeeRepository.delete(employee);
		Optional<Employee> deletedEmp = employeeRepository.findById(1);
		Assertions.assertThat(deletedEmp).isEmpty();
	}

}

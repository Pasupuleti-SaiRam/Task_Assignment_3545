package com.employee.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.employee.entities.Department;
import com.employee.entities.Employee;
import com.employee.repositories.DepartmentRepository;
import com.employee.repositories.EmployeeRepository;
import com.employee.serviceImpl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	@Mock
	private EmployeeRepository employeeRepository;
	@Mock
	private DepartmentRepository departmentRepository;
	Employee employee;
	@BeforeEach
	void setUp() {
		employee=new Employee();
		employee.setName("Sairam");
		employee.setGender("Male");
		employee.setDesignation("Technical Associate");
		employee.setSalary(45000.00);
		employee.setDate(LocalDate.now());
		
	}

	@Test
	public void testSaveEmployee() {
		
		employee=new Employee();
		employee.setName("Sairam");
		employee.setGender("Male");
		employee.setDesignation("Technical Associate");
		employee.setSalary(45000.00);
		employee.setDate(LocalDate.now());
		
    Department department = new Department();
    department.setId(1);
    department.setName("IT");
    employee.setDepartments(List.of(department));
    
    when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
    when(employeeRepository.save(employee)).thenReturn(employee);

    // Act
    Employee savedEmployee = employeeService.addEmployee(employee);

    // Assert
    assertNotNull(savedEmployee);
    assertEquals(employee, savedEmployee);

    verify(departmentRepository, times(1)).findById(1);
    verify(employeeRepository, times(1)).save(employee);
}
	@Test
	public void testGetEmployees() {
		List<Employee> employeesList = new ArrayList<>();
		
		employeesList.add(employee);
		
		employee=new Employee();
		employee.setName("Kumar");
		employee.setGender("Male");
		employee.setDesignation("Technical Associate");
		employee.setSalary(45000.00);
		employee.setDate(LocalDate.now());
		employeesList.add(employee);
		
		when(employeeRepository.findAll()).thenReturn(employeesList);
		List<Employee> actual = employeeService.getEmployees();
		assertThat(actual).isEqualTo(employeesList);
	}
	@Test
	public void testGetEmployeeById() {
		
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		Employee actual = employeeService.findEmployeeById(1);
		assertThat(actual).isEqualTo(employee);
	}
	

	@Test
	public void testUpdateEmployee() {
	    
	    Employee existingEmployee = new Employee();
	    existingEmployee.setId(1);
	    existingEmployee.setName("Kumar");
	    existingEmployee.setGender("Male");
	    existingEmployee.setDesignation("Technical Associate");
	    existingEmployee.setSalary(45000.00);
	    existingEmployee.setDate(LocalDate.now());

	    Department existingDepartment = new Department();
	    existingDepartment.setId(1);
	    existingDepartment.setName("IT");
	    
	    Employee updatedEmployee = new Employee();
	    updatedEmployee.setId(1);
	    updatedEmployee.setName("Ram");
	    updatedEmployee.setGender("Male");
	    updatedEmployee.setDesignation("Technical Associate");
	    updatedEmployee.setSalary(45000.00);
	    updatedEmployee.setDate(LocalDate.now());
	    updatedEmployee.setDepartments(List.of(existingDepartment));

	    when(employeeRepository.findById(1)).thenReturn(Optional.of(existingEmployee));
	    when(departmentRepository.findById(1)).thenReturn(Optional.of(existingDepartment));
	    when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

	    Employee actualResult = employeeService.updateEmployee(1, updatedEmployee);

	    assertEquals("Ram", actualResult.getName());
	}
	@Test
	public void deleteEmployee() {
		
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		String deletedEmployee = employeeService.deleteEmployee(1);
		  assertNotNull(deletedEmployee);
		
		
	}

}

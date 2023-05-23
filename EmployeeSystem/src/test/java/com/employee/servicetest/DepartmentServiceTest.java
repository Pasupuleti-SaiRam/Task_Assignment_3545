package com.employee.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
import com.employee.repositories.DepartmentRepository;
import com.employee.serviceImpl.DepartmentServiceImpl;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
	
	@InjectMocks
	private DepartmentServiceImpl departmentService;
	@Mock
	private DepartmentRepository departmentRepository;
	private Department department;
	@BeforeEach
	void setUp() {
		department=new Department();
		department.setId(1);
		department.setName("Java");
	}
	@Test
	public void testCreateDepartment() {
		
		when(departmentRepository.save(department)).thenReturn(department);
		Department createdDepartment = departmentService.createDepartment(department);
		assertEquals(createdDepartment, department);
		assertNotNull(createdDepartment);
		
	}
	@Test
	public void testGetDepartment() {
		when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
		Department actualDepartment = departmentService.findDepartmentById(1);
		assertEquals(actualDepartment, department);
		
	}
	@Test
	public void testListOfDepartments() {
		List<Department> departments=new ArrayList<>();
		department.setName("Angular");
		departments.add(department);
		
		when(departmentRepository.findAll()).thenReturn(departments);
		List<Department> listOfDepartments = departmentService.getDepartments();
		assertThat(listOfDepartments.size()).isGreaterThan(0);
	}
	@Test
	public void testUpdateDepartment() {
		when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
		
		System.out.println(department);
		department.setName(".Net");
		when(departmentRepository.save(department)).thenReturn(department);
		Department updateDepartment = departmentService.updateDepartment(1, department);
		System.out.println(updateDepartment);
		assertEquals(updateDepartment.getName(), ".Net");
		
	}
	@Test
	public void testDeleteDepartment() {
		when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
		String delateDepartment = departmentService.delateDepartment(1);
		assertNotNull(delateDepartment);
	}

}

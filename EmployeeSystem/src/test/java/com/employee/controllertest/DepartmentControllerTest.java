package com.employee.controllertest;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;

import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employee.controllers.DepartmentController;
import com.employee.entities.Department;
import com.employee.repositories.DepartmentRepository;
import com.employee.serviceImpl.DepartmentServiceImpl;


public class DepartmentControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private DepartmentServiceImpl departmentService;
	
	@InjectMocks
	private DepartmentController departmentController;
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
	}
	
	@Test
	public void testGetDepartments() throws Exception {
		Department department = new Department();
		department.setId(1);
		department.setName("IT");
		Department department1 = new Department();
		department.setId(1);
		department.setName("IT");
		List<Department> departmentsList= Arrays.asList(department,department1);
		//employeeList.add(employee,employee1);
		
		when(departmentService.getDepartments()).thenReturn(departmentsList);
		
		mockMvc.perform(get("/department/"))
			.andExpect(status().isOk());
			//.andExpect(content().json("[{id: 1, name: 'John Doe'}, {id: 2, name: 'Jane Smith'}]"));
	}	
	
	@Test
	public void testDepartmentById() throws Exception {
		Department department = new Department();
		department.setId(1);
		department.setName("IT");
		
		when(departmentService.findDepartmentById(1)).thenReturn(department);
		
		mockMvc.perform(get("/department/1"))
			.andExpect(status().isOk());
			
		
	}
	
	@Test
	public void testCreateDepartment() throws Exception {
		Department department = new Department();

		department.setId(1);
		department.setName("IT");
		when(departmentRepository.save(department)).thenReturn(department);
		

		mockMvc.perform(post("/department/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"IT\"}"))
				.andExpect(status().isCreated());

		
	}
	
	@Test
	public void testDeleteDepartment() throws Exception {
		when(departmentService.delateDepartment(1)).thenReturn("Record Deleted");
		
		mockMvc.perform(delete("/department/1"))
			.andExpect(status().isOk())
			.andExpect(content().string("Record Deleted"));
		
	
	}

}

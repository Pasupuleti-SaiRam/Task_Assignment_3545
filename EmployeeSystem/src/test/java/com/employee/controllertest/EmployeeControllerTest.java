package com.employee.controllertest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employee.controllers.EmployeeController;
import com.employee.entities.Department;
import com.employee.entities.Employee;
import com.employee.repositories.DepartmentRepository;
import com.employee.repositories.EmployeeRepository;
import com.employee.serviceImpl.EmployeeServiceImpl;


public class EmployeeControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private EmployeeServiceImpl employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private DepartmentRepository departmentRepository;
	@Mock
	private EmployeeRepository employeeRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}
	
	@Test
	public void testGetEmployees() throws Exception {
		Employee employee = new Employee();
		employee.setName("sai");
		employee.setGender("male");
		employee.setDesignation("developer");
		employee.setSalary(32000.00);
		employee.setDate(LocalDate.now());
		Employee employee1 = new Employee();
		employee.setName("ram");
		employee.setGender("male");
		employee.setDesignation("developer");
		employee.setSalary(32000.00);
		employee.setDate(LocalDate.now());
		List<Employee> employeeList= Arrays.asList(employee,employee1);
		//employeeList.add(employee,employee1);
		
		when(employeeService.getEmployees()).thenReturn(employeeList);
		
		mockMvc.perform(get("/employee"))
			.andExpect(status().isOk());
			//.andExpect(content().json("[{id: 1, name: 'John Doe'}, {id: 2, name: 'Jane Smith'}]"));
	}	
	
	@Test
	public void testGetEmployeeById() throws Exception {
		Employee employee = new Employee();
		employee.setName("sai");
		employee.setGender("male");
		employee.setDesignation("developer");
		employee.setSalary(32000.00);
		employee.setDate(LocalDate.now());
		
		when(employeeService.findEmployeeById(1)).thenReturn(employee);
		
		mockMvc.perform(get("/1"))
			.andExpect(status().isOk());
			
		
	}
	
	@Test
	public void testAddEmployeeToDept() throws Exception {
		Employee employee = new Employee();
		employee.setName("sai");
		employee.setGender("male");
		employee.setDesignation("developer");
		employee.setSalary(32000.00);
		employee.setDate(LocalDate.now());

		Department department = new Department();
		department.setId(1);
		department.setName("IT");
		employee.setDepartments(Collections.singletonList(department));

		when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(department));
		when(employeeRepository.save(employee)).thenReturn(employee);

		mockMvc.perform(post("/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"sai\", \"gender\": \"male\", \"designation\": \"developer\", \"salary\": 32000.00, \"date\": \"2023-05-23\", \"departments\": [{\"id\": 1, \"name\": \"IT\"}]}"))
				.andExpect(status().isCreated());

		
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		when(employeeService.deleteEmployee(1)).thenReturn("Record Deleted");
		
		mockMvc.perform(delete("/1"))
			.andExpect(status().isOk())
			.andExpect(content().string("Record Deleted"));
		
	
	}
}

package com.employee.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entities.Department;
import com.employee.entities.Employee;
import com.employee.exceptions.DepartmentNotFoundException;
import com.employee.repositories.DepartmentRepository;
import com.employee.service.DepartmentService;

@Service
public class DepartmentServiceImpl  implements DepartmentService{
	@Autowired
	private DepartmentRepository departmentRepo;
	

	@Override
	public Department createDepartment(Department department) {
		return departmentRepo.save(department);
		
	}

	

	@Override
	public Department findDepartmentById(Integer id) {
		 return departmentRepo.findById(id).orElseThrow(()-> new DepartmentNotFoundException("Department is not found with this id"+" : "+id));
	
	}

	@Override
	public List<Department> getDepartments() {
		return departmentRepo.findAll();
	}

	@Override
	public String delateDepartment(Integer id) {
		Department department = departmentRepo.findById(id).orElseThrow(()-> new DepartmentNotFoundException("Department is not found with this id"+" : "+id));
		departmentRepo.delete(department);

		return "Record Deleted";
	}

	

	@Override
	public Department updateDepartment(Integer id, Department department) {
		Department existingdepartment = departmentRepo.findById(id).orElseThrow(()-> new DepartmentNotFoundException("Department is not found with this id"+" : "+id));
		existingdepartment.setName(department.getName());
		return departmentRepo.save(existingdepartment);
	}

	

}

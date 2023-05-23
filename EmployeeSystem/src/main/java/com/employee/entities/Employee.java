package com.employee.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_details")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id")
	private Integer id;
	@Column(name="employee_name")
	private String name;
	@Column(name = "gender")
	private String gender;
	@Column(name="salary")
	private double salary;
	@Column(name="designation")
	private String designation;
	@CreationTimestamp
	@Column(updatable = false,name = "date_of_joining")
	private LocalDate date;
	
	@ManyToMany(cascade = { CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "department_employee", joinColumns = @JoinColumn(name="employee_id"),
	               inverseJoinColumns = @JoinColumn(name = "department_id"))
	 
	    private List<Department> departments = new ArrayList<>();
	 
	 public Employee() {
		 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", salary=" + salary + ", designation="
				+ designation + ", date=" + date + ", departments=" + departments + "]";
	}
	 
	

}

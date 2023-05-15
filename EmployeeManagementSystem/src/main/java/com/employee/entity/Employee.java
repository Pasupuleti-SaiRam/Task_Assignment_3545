package com.employee.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "employee_details")
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="employee_id")
	private Integer id;
	@Column(name="employee_name")
	private String name;
	@Column(name="employee_email")
	private String email;
	@Column(name="employee_number")
	private String phoneNumber;
	@Column(name = "employee_salary")
	private double salary;
	@CreationTimestamp
	@Column(updatable = false,name = "createdOn")
	private LocalDate createdOn;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;
//	private Integer departmentId;
//	
//	public void setDepartmentId(Integer departmentId) {
//	    this.departmentId = departmentId;
//	}

}

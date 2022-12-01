package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	 private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	 //build create employee REST API
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
	}
	
	//build get all employee REST API
	@GetMapping
	public List<Employee> getAllEmployees()
	{
		return employeeService.getEmployees();
	}
	
	//build get employee by id REST API
	//http://localhost:8080/api/employee/1
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployById(@PathVariable("id") long eId)
	{
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(eId),HttpStatus.OK);
	}
	
	//build update employee REST API
	//http://localhost:8080/api/employee/1
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") long id,@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id),HttpStatus.OK);
	}
	
	//build delete employee REST API
	//http://localhost:8080/api/employee/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id)
	{
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Deleted",HttpStatus.OK);
	}
}

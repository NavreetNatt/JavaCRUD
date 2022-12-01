package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee=employeeRepository.findById(id);
		if(employee.isPresent())
		return employee.get();
		else {
			throw new ResourceNotFoundException("Employee", "Id", id); 
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//check if employee with given id exit in db or not
		Employee exisitingEmployee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id)); 
		exisitingEmployee.setFirstName(employee.getFirstName());
		exisitingEmployee.setLastName(employee.getLastName());
		exisitingEmployee.setEmail(employee.getEmail());
		//save existing employee by id
		employeeRepository.save(exisitingEmployee);
		return exisitingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
	}
	

}

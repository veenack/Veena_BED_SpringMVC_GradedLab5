package com.gl.employeeManagementSystem.service.impl;

import java.util.List;
import java.util.Optional;

import com.gl.employeeManagementSystem.entity.Employee;
import com.gl.employeeManagementSystem.repository.EmployeeRepository;
import com.gl.employeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> result = this.employeeRepository.findById(id);
        if (result.isPresent()) {
           return result.get();
        } else {
            // we didn't find the book
            throw new RuntimeException("Did not find employee id - " + id);
        }
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee existingEmployee = this.getEmployeeById(id);
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = this.getEmployeeById(id);
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setFirstName(employee.getFirstName());
        return this.employeeRepository.save(existingEmployee);

    }
}

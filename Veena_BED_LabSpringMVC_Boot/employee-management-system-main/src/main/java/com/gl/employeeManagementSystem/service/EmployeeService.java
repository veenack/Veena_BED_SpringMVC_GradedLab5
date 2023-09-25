package com.gl.employeeManagementSystem.service;

import java.util.List;

import com.gl.employeeManagementSystem.entity.Employee;

public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(Long id);

    public Employee saveEmployee(Employee employee);

    public void deleteEmployeeById(Long id);

    public Employee updateEmployee(Long id,Employee employee);

}

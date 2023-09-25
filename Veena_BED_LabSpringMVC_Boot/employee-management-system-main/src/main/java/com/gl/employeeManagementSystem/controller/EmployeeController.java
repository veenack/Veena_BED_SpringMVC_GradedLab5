package com.gl.employeeManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gl.employeeManagementSystem.entity.Employee;
import com.gl.employeeManagementSystem.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
public String showEmployess(Model model){
   // model.addAttribute("message","Welcome to SpringBoot Thymeleaf UI");
        List<Employee> employees = this.employeeService.getAllEmployees();
        model.addAttribute("employees",employees);
   return "employees";
}

    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model){
        // model.addAttribute("message","Welcome to SpringBoot Thymeleaf UI");
       Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "create_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){

        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeesForm(@PathVariable("id") Long id, Model model){
       Employee employee = this.employeeService.getEmployeeById(id);
       model.addAttribute("employee",employee);
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee){
        this.employeeService.updateEmployee(id,employee);
         return "redirect:/employees";
    }

    @GetMapping("employees/{id}")
    public String deleteEmployee(@PathVariable("id") Long id)   {
        this.employeeService.deleteEmployeeById(id);

        return "redirect:/employees";
    }
}

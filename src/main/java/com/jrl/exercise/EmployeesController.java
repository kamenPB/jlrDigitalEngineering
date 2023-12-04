package com.jrl.exercise;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeesController {

    private EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @RequestMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping("/employees/{department}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String department) {
        return employeeService.getAllEmployeesByDepartment(department);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public String addNewEmployee(@RequestBody Employee employee) {
        return employeeService.addNewEmployee(employee);
    }

    @RequestMapping("/departments")
    public List<Department> getDistinctDepartmentNames() {
        return employeeService.getDistinctDepartmentNames();
    }

    @RequestMapping("/departments/top")
    public List<Employee> getTopEarnersInEachDepartment() {
        return employeeService.getTopEarnersInEachDepartment();
    }

    @RequestMapping("departments/count")
    public List<EmployeeCounter> getEmployeeNumberForEachDepartment() {
        return employeeService.getEmployeeNumberForEachDepartment();
    }

}

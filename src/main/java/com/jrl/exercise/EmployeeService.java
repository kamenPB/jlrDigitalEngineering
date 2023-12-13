package com.jrl.exercise;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static List<Employee> employeeList = new ArrayList<>();
    private static List<Department> departmentList = new ArrayList<>();

    static {

        Department itDep = new Department(1, "IT");
        Department financeDep = new Department(2, "Finance");
        Department adminDep = new Department(3, "Admin");
        departmentList.add(itDep);
        departmentList.add(financeDep);
        departmentList.add(adminDep);

        Employee emp1 = new Employee(1, "A", 10000, itDep);
        Employee emp2 = new Employee(2, "B", 20000, itDep);
        Employee emp3 = new Employee(3, "C", 15000, financeDep);
        Employee emp4 = new Employee(4, "D", 50000, adminDep);
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
        employeeList.add(emp4);

    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public void deleteEmployeeById(int id) {
        employeeList = employeeList.stream().filter(employee -> employee.getId() != id).collect(Collectors.toList());
    }

    public List<Employee> getAllEmployeesByDepartment(String department) {
        List<Employee> employeesInDepartment;

        employeesInDepartment = employeeList.stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals(department)).collect(Collectors.toList());

        return employeesInDepartment;
    }

    public String addNewEmployee(Employee employee) {
        if (isEmployeeIdPresent(employee.getId())) {
            return "Employee id is already present!";
        }
        employeeList.add(employee);
        return "Employee added successfully";
    }

    public boolean isEmployeeIdPresent(long id) {
        for (Employee employee : employeeList) {
            if (id == employee.getId()) {
                return true;
            }
        }
        return false;
    }

    public List<Department> getDistinctDepartmentNames() {
        return employeeList.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Employee> getTopEarnersInEachDepartment() {
        List<Employee> topEarnersList = new ArrayList<>();

        departmentList.forEach(department -> checkTopEarnerInParticularDepartment(department, topEarnersList));

        return topEarnersList;
    }

    private void checkTopEarnerInParticularDepartment(Department department, List<Employee> topEarnersList) {
        Employee topEarner = employeeList.stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals(department.getDepartmentName()))
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);

        topEarnersList.add(topEarner);
    }

    public List<EmployeeCounter> getEmployeeNumberForEachDepartment() {
        List<EmployeeCounter> employeeCounterList = new ArrayList<>();
        departmentList.forEach(department -> checkEmployeeCountForParticularDepartment(department, new EmployeeCounter(), employeeCounterList));
        return employeeCounterList;
    }

    private void checkEmployeeCountForParticularDepartment(Department department, EmployeeCounter employeeCounter, List<EmployeeCounter> employeeCounterList) {
        employeeCounter.setDepartmentName(department.getDepartmentName());
        employeeCounter.setEmployeeCounter(((int) employeeList.stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals(department.getDepartmentName()))
                .count()));
        employeeCounterList.add(employeeCounter);
    }
}

package com.jrl.exercise;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class EmployeeService {

    private static List<Employee> employeeList = new ArrayList<>();
    private static List<Department> departmentList = new ArrayList<>();

    static {

        Employee emp1 = new Employee(1, "A", 10000, new Department(1, "IT"));
        Employee emp2 = new Employee(2, "B", 20000, new Department(1, "IT"));
        Employee emp3 = new Employee(3, "C", 15000, new Department(2, "Finance"));
        Employee emp4 = new Employee(4, "D", 50000, new Department(3, "Admin"));

        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
        employeeList.add(emp4);

    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public List<Employee> getAllEmployeesByDepartment(String department) {
        List<Employee> employeesInDepartment = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getDepartment().getDepartmentName().equals(department)) {
                employeesInDepartment.add(employee);
            }
        }
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
        for (Employee employee : employeeList) {
            if (!isDepartmentPresent(employee.getDepartment().getDepartmentName())) {
                departmentList.add(employee.getDepartment());
            }
        }
        return departmentList;
    }

    private boolean isDepartmentPresent(String departmentName) {
        for (Department department : departmentList) {
            if (department.getDepartmentName().equals(departmentName)) {
                return true;
            }
        }
        return false;
    }

    public String getTopEarnersInEachDepartment() {
        HashMap<String, List<Employee>> topEarnersInDepartment = new HashMap<>();
        for (Employee employee : employeeList) {
            List<Employee> topEarningEmployeeList = topEarnersInDepartment.get(employee.getDepartment().getDepartmentName());
            if (topEarningEmployeeList != null) {
                for (Employee employeeInDepartment : topEarningEmployeeList) {
                    if (employee.getSalary() > employeeInDepartment.getSalary()) {
                        if (employee.getSalary() != employeeInDepartment.getSalary()) {
                            topEarningEmployeeList.remove(employeeInDepartment);
                        }
                        topEarningEmployeeList.add(employee);
                    }
                }
            } else {
                topEarnersInDepartment.put(employee.getDepartment().getDepartmentName(), new ArrayList<>(Arrays.asList(employee)));
            }
        }

        if (!topEarnersInDepartment.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry entry : topEarnersInDepartment.entrySet()) {
                stringBuilder.append(entry.getKey()).append("--->").append(entry.getValue().toString());
            }
            return stringBuilder.toString();
        } else return null;

    }

    public String getEmployeeNumberForEachDepartment() {
        HashMap<String, Integer> employeeCounterMap = new HashMap<>();
        for (Employee employee : employeeList) {
            Integer employeeCounter = employeeCounterMap.get(employee.getDepartment().getDepartmentName());
            if (employeeCounter != null) {
                employeeCounterMap.put(employee.getDepartment().getDepartmentName(), employeeCounter + 1);
            } else {
                employeeCounterMap.put(employee.getDepartment().getDepartmentName(), 1);
            }
        }

        if (!employeeCounterMap.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry entry : employeeCounterMap.entrySet()) {
                stringBuilder.append(entry.getKey()).append("--->").append(entry.getValue());
            }
            return stringBuilder.toString();
        } else return null;
    }
}

package com.jrl.exercise;

public class EmployeeCounter {

    private String departmentName;
    private int employeeCounter;

    public EmployeeCounter() {

    }

    public EmployeeCounter(String departmentName, int employeeCounter) {
        this.departmentName = departmentName;
        this.employeeCounter = employeeCounter;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getEmployeeCounter() {
        return employeeCounter;
    }

    public void setEmployeeCounter(int employeeCounter) {
        this.employeeCounter = employeeCounter;
    }
}

package com.jrl.exercise;

public class Department {

    private long id;
    private String departmentName;

    public Department(long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}

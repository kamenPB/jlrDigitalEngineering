package com.jrl.exercise;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String DEPARTMENT_INSERT_QUERY = "insert into departments (departmentName) values (?);";
    private static final String DEPARTMENT_DELETE_QUERY = "delete from departments where id = ?";

    private static final String EMPLOYEE_INSERT_QUERY = "insert into employees (name, age, departmentId) values (?, ?, ?);";
    private static final String EMPLOYEE_DELETE_QUERY = "delete from employees where id = ?";

    @Transactional
    public void insertDepartment(Department department) {
        jdbcTemplate.update(DEPARTMENT_INSERT_QUERY, department.getDepartmentName());
    }

    public void deleteDepartmentById(long id) {
        jdbcTemplate.update(DEPARTMENT_DELETE_QUERY, id);
    }

    @Transactional
    public void insertEmployee(Employee employee) {
        jdbcTemplate.update(EMPLOYEE_INSERT_QUERY, employee.getName(), employee.getAge(), employee.getDepartmentId());
    }

    public void deleteEmployeeById(long id) {
        jdbcTemplate.update(EMPLOYEE_DELETE_QUERY, id);
    }



}

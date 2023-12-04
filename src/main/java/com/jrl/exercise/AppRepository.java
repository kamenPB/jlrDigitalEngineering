package com.jrl.exercise;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String DEPARTMENT_INSERT_QUERY = "insert into department (id, departmentName) values (?, ?);";
    private static final String DEPARTMENT_DELETE_QUERY = "delete from department where id = ?";

    private static final String EMPLOYEE_INSERT_QUERY = "insert into employee (id, name, age, departmentId) values (?, ?, ?, ?);";
    private static final String EMPLOYEE_DELETE_QUERY = "delete from employee where id = ?";

    @Transactional
    public void insertDepartment(Department department) {
        jdbcTemplate.update(DEPARTMENT_INSERT_QUERY, department.getId(), department.getDepartmentName());
    }

    public void deleteDepartmentById(long id) {
        jdbcTemplate.update(DEPARTMENT_DELETE_QUERY, id);
    }

    @Transactional
    public void insertEmployee(Employee employee) {
        jdbcTemplate.update(EMPLOYEE_INSERT_QUERY, employee.getId(), employee.getName(), employee.getAge(), employee.getDepartmentId());
    }

    public void deleteEmployeeById(long id) {
        jdbcTemplate.update(EMPLOYEE_DELETE_QUERY, id);
    }



}

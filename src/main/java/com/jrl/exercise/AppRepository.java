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
    private static final String DEPARTMENT_UPDATE_QUERY = "";

    private static final String EMPLOYEE_INSERT_QUERY = "insert into employees (name, age, departmentId) values (?, ?, ?);";
    private static final String EMPLOYEE_DELETE_QUERY = "delete from employees where id = ?";
    private static final String EMPLOYEE_UPDATE_QUERY = "";

    // TODO modify query so that it displays multiple departments when number of employees is the same as MAX (dont limit to just 1)
    private static final String GET_DEPT_WITH_MAX_EMPLOYEES_QUERY = "select dep.id as department_id, dep.departmentName, count(emp.id) as employee_count" +
            "                                                           from departments dep" +
            "                                                               join employees emp on dep.id = emp.departmentId" +
            "                                                           group by dep.id, dep.departmentName" +
            "                                                        order by employee_count desc limit 1";
    private static final String GET_EMPLOYEES_IN_SPECIFIC_DEPT_QUERY = "select * from employees emp " +
            "                                                               join departments dep on emp.departmentId = dep.id " +
            "                                                           where dep.departmentName = '?'";
    private static final String GET_EMPLOYEES_WITHIN_RANGE_QUERY = "select * from employees where age between ? and ?";
    private static final String GET_EMPLOYEES_IN_SPECIFIC_DEPT_YOUNGER_THAN_SPECIFIC_AGE_QUERY = "select * from employees emp " +
            "                                                                                       join departments dep on emp.departmentId = dep.id " +
            "                                                                                     where dep.departmentName = '?'" +
            "                                                                                     and emp.age < ?";
    private static final String GET_EMPLOYEES_OLDER_THAN_SPECIFIC_AGE_NOT_IN_SPECIFIC_DEPT_QUERY = "select * from employees emp " +
            "                                                                                           join departments dep on emp.departmentId = dep.id " +
            "                                                                                       where dep.departmentName != '?'" +
            "                                                                                       and emp.age > ?";



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

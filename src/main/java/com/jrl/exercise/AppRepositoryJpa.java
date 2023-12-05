package com.jrl.exercise;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AppRepositoryJpa {

    @PersistenceContext
    public EntityManager entityManager;

    public void insertDepartment(Department department) {
        entityManager.persist(department);
    }

    public Department findDepartmentById(long id) {
        return entityManager.find(Department.class, id);
    }

    public void deleteDepartmentById(long id) {
        Department department = findDepartmentById(id);
        entityManager.remove(department);
    }

    public void insertEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    public Employee findEmployeeById(long id) {
        return entityManager.find(Employee.class, id);
    }

    public void deleteEmployeeById(long id) {
        Employee employee = findEmployeeById(id);
        entityManager.remove(employee);
    }

}

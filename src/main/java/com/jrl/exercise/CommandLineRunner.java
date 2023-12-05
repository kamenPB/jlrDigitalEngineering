package com.jrl.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    @Autowired
    private AppRepository appRepository;

    @Override
    public void run(String... args) throws Exception {

        appRepository.insertDepartment(new Department("Finance"));
        appRepository.insertDepartment(new Department("IT"));

        try {
            appRepository.insertEmployee(new Employee("Kamen", 25, 1));
            appRepository.insertEmployee(new Employee("Moni", 29, 1));
            appRepository.insertEmployee(new Employee("Moni1", 29, 2));
            appRepository.insertEmployee(new Employee("Moni2", 29, 1));
//            appRepository.insertEmployee(new Employee("Moni3", 29, 3));
        } catch (Exception ex) {
            throw new Exception("One of the insert operations failed! ---> " + ex.getMessage());
        }

        appRepository.deleteEmployeeById(1);
//        appRepository.deleteDepartmentById(2);

    }
}

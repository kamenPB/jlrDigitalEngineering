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
        appRepository.insertEmployee(new Employee("Kamen", 25, 1));
        appRepository.insertEmployee(new Employee("Moni", 29, 1));

//        appRepository.deleteEmployeeById(1);

    }
}

package com.jrl.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    @Autowired
    private AppRepository appRepository;

    @Override
    public void run(String... args) throws Exception {

        appRepository.insertDepartment(new Department(1, "Finance"));
        appRepository.insertEmployee(new Employee(1, "Kamen", 25, 1));
        appRepository.insertEmployee(new Employee(2, "Moni", 29, 1));

//        appRepository.deleteEmployeeById(1);

    }
}

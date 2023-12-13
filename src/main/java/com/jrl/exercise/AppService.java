package com.jrl.exercise;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppService {


    @Autowired
    private AppRepositoryJpa appRepository;
    public void method() throws Exception {

        appRepository.insertDepartment(new Department("Finance"));
        appRepository.insertDepartment(new Department("IT"));

        try {
            appRepository.insertEmployee(new Employee("Kamen", 25, 1));
            appRepository.insertEmployee(new Employee("Kamen2", 25, 1));
            appRepository.insertEmployee(new Employee("Moni", 26, 1));
            appRepository.insertEmployee(new Employee("Peter", 27, 2));
            appRepository.insertEmployee(new Employee("Simon", 28, 1));
//            appRepository.insertEmployee(new Employee("Tray", 29, 3));
        } catch (Exception ex) {
            throw new Exception("One of the insert operations failed! ---> " + ex.getMessage());
        }

        appRepository.deleteEmployeeById(2);
    }

}

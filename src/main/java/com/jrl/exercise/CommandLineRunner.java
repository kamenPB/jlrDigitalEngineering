package com.jrl.exercise;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

//    @Autowired
//    private AppRepository appRepository;

//    @Autowired
//    private AppRepositoryJpa appRepository;

    @Autowired
    AppService appService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        appService.method();
//                .insertDepartment(new Department("Finance"));
//
//        appRepository.insertDepartment(new Department("IT"));
//
//        try {
//            appRepository.insertEmployee(new Employee("Kamen", 25, 1));
//            appRepository.insertEmployee(new Employee("Kamen2", 25, 1));
//            appRepository.insertEmployee(new Employee("Moni", 26, 1));
//            appRepository.insertEmployee(new Employee("Peter", 27, 2));
//            appRepository.insertEmployee(new Employee("Simon", 28, 1));
////            appRepository.insertEmployee(new Employee("Tray", 29, 3));
//        } catch (Exception ex) {
//            throw new Exception("One of the insert operations failed! ---> " + ex.getMessage());
//        }
//
//        appRepository.deleteEmployeeById(2);
//        appRepository.deleteDepartmentById(2);

//        List<Object[]> getDepartmentWithMaxEmployeesResult = appRepository.getDepartmentWithMaxEmployees();
//        System.out.println("getDepartmentWithMaxEmployees");
//        System.out.println("department_id, departmentName, employee_count");
//        for (Object[] strings : getDepartmentWithMaxEmployeesResult) {
//            System.out.println(Arrays.toString(strings));
//        }

//        List<Object[]> getEmployeesInSpecificDepartmentResult = appRepository.getEmployeesInSpecificDepartment("Finance");
//        System.out.println("getEmployeesInSpecificDepartment");
//        System.out.println("Tables joined");
//        for (Object[] strings : getEmployeesInSpecificDepartmentResult) {
//            System.out.println(Arrays.toString(strings));
//        }


    }
}

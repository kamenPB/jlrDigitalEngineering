package com.jrl.exercise;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="departmentId")
    private Department department; // department object

    public Employee() {

    }

    public Employee(String name, int age, Department department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getDepartmentId() {
        return departmentId;
    }
}

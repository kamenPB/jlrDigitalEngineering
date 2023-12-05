package com.jrl.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long departmentId;

    public Employee() {

    }

    public Employee(String name, int age, long departmentId) {
        this.name = name;
        this.age = age;
        this.departmentId = departmentId;
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

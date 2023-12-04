package com.jrl.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private long departmentId;

    public Employee() {

    }

    public Employee(long id, String name, int age, long departmentId) {
        super();
        this.id = id;
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

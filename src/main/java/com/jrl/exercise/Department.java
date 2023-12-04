package com.jrl.exercise;

import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(mappedBy="departmentId")
    private long id;
    private String departmentName;

    public Department() {

    }

    public Department(long id, String departmentName) {
        super();
        this.id = id;
        this.departmentName = departmentName;
    }

    public long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}

package com.example.thymeleaf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private Integer gender;
    private String email;
    private String lastName;
    private Department department;
    private Date birth;

    public Employee(Integer id, Integer gender, String email, String lastName, Department department) {
        this.id = id;
        this.gender = gender;
        this.email = email;
        this.lastName = lastName;
        this.department = department;
        this.birth = new Date();
    }
}

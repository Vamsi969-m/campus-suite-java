package com.example.campus_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message="Name Cannot Be Empty")
    private String name;

    @Email(message="Enter a valid Email address")
    private String email;

    @NotBlank(message="Department cannot be Empty")
    private String department;

    @Min(value=1,message="Year must be atleast 1 Year")
    @Max(value=4,message="Year Cannot exceed 4")
    private int year;

    public Student(){

    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getDepartment(){
        return department;
    }

    public int getYear(){
        return year;
    }

    public void setId(){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setDepartment(String department){
        this.department=department;
    }

    public void setYear(int year){
        this.year=year;
    }
}

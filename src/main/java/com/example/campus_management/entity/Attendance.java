package com.example.campus_management.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name="attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate localDate;

    private String status;

    @ManyToOne
    @JoinColumn(name="student_id",nullable = false)
    private Student student;

    public Attendance(){

    }

    public Long getId(){
        return id;
    }

    public LocalDate getAttendanceDate() {
        return localDate;
    }

    public void setAttendanceDate(LocalDate localDateTime){
        this.localDate=localDateTime;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student=student;
    }

}

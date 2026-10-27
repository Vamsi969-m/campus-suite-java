package com.example.campus_management.repository;

import com.example.campus_management.entity.Student;
import org.springframework.data.jpa.repository.*;


import java.util.*;

public interface StudentRepository
        extends JpaRepository<Student, Long> {
    List<Student> findByDepartment(String department);

    List<Student> findByYear(int year);

    @Query("select s.department,count(s) from Student s group by s.department")
    List<Object[]> countStudentByDepartment();

    List<Student> findByName(String name);
}

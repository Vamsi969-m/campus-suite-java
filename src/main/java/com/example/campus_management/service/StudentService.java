package com.example.campus_management.service;

import com.example.campus_management.entity.Student;
import com.example.campus_management.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id,Student updatedStudent){
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student Not Found By ID :"+id));

        student.setName(updatedStudent.getName());
        student.setDepartment(updatedStudent.getDepartment());
        student.setEmail(updatedStudent.getEmail());
        student.setYear(updatedStudent.getYear());

        return studentRepository.save(student);
    }

    public Student getStudentById(long id){
        return studentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found with ID :"+id));
    }

    public List<Student> findByDepartment(String department){
        return studentRepository.findByDepartment(department);
    }

    public List<Student> findByYear(int year){
        return studentRepository.findByYear(year);
    }

    public Page<Student> getStudentByPage(int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        return studentRepository.findAll(pageable);
    }

    public List<Student> getStudentsSorted(String field,String direction){
        Sort sort=direction.equalsIgnoreCase("desc")?Sort.by(field).descending():Sort.by(field).ascending();
        return studentRepository.findAll(sort);
    }

    public long getStudentCount(){
        return studentRepository.count();
    }

    public List<Object[]> countStudentByDepartment(){
        return studentRepository.countStudentByDepartment();
    }

    public List<Student> findByName(String name){
        return studentRepository.findByName(name);
    }
}

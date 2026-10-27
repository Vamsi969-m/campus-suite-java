package com.example.campus_management.controller;

import com.example.campus_management.entity.Student;
import com.example.campus_management.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable long id,@RequestBody Student student){
        return studentService.updateStudent(id,student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/department/{department}")
    public List<Student> getStudentByDepartment(@PathVariable String department){
        return studentService.findByDepartment(department);
    }

    @GetMapping("year/{year}")
    public List<Student> getStudentByYear(@PathVariable int year){
        return studentService.findByYear(year);
    }

    @GetMapping("/page")
    public Page<Student> getStudentsByPage(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5")int size){
        return studentService.getStudentByPage(page,size);
    }

    @GetMapping("/sort")
    public List<Student> getStudentsSorted(@RequestParam(defaultValue="name") String field,@RequestParam(defaultValue="desc") String direction){
        return studentService.getStudentsSorted(field,direction);
    }

    @GetMapping("/count")
    public long getStudentCount(){
        return studentService.getStudentCount();
    }

    @GetMapping("/countStudentsByDepartment")
    public List<Object[]> countStudentsByDepartment(){
        return studentService.countStudentByDepartment();
    }

    @GetMapping("/Name")
    public List<Student> findByName(@RequestParam String name){
        return studentService.findByName(name);
    }
}

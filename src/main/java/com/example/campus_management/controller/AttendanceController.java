package com.example.campus_management.controller;

import com.example.campus_management.entity.Attendance;
import com.example.campus_management.repository.AttendanceRepository;
import com.example.campus_management.service.AttendanceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/Attendance")
public class AttendanceController {
    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService=attendanceService;
    }

    @PostMapping("/{studentId}")
    public Attendance markAttendance(@RequestParam String status, @PathVariable long studentId,@RequestParam
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date){
        return attendanceService.markAttendance(status,studentId,date);
    }

    @GetMapping
    public List<Attendance> getAllAttendance(){
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/{studentId}")
    public List<Attendance> getAttendanceByStudentId(@PathVariable Long studentId){
        return attendanceService.findByStudentId(studentId);
    }

    @GetMapping("/student/{studentId}/summary")
    public Map<String,Object> getAttendanceSummary(@PathVariable Long studentId){
        return attendanceService.getAttendanceSummary(studentId);
    }
}

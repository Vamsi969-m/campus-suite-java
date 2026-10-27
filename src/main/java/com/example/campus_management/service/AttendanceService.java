package com.example.campus_management.service;

import com.example.campus_management.entity.Attendance;
import com.example.campus_management.entity.Student;
import com.example.campus_management.repository.AttendanceRepository;
import com.example.campus_management.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import java.time.*;


@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,StudentRepository studentRepository){
        this.attendanceRepository=attendanceRepository;
        this.studentRepository=studentRepository;
    }

    public Attendance markAttendance(String status, Long studentId, LocalDate localDate){
        if(!status.equalsIgnoreCase("PRESENT") && !status.equalsIgnoreCase("ABSENT")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Status must be present or abscent");
        }

        Student student=studentRepository.findById(studentId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"+studentId));

        Attendance attendance=new Attendance();
        attendance.setStudent(student);
        attendance.setStatus(status.toUpperCase());
        attendance.setAttendanceDate(localDate);

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance(){
        return attendanceRepository.findAll();
    }

    public List<Attendance> findByStudentId(Long studentId){
        return attendanceRepository.findByStudentId(studentId);
    }

    public Map<String,Object> getAttendanceSummary(Long studentId){
        Student student=studentRepository.findById(studentId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student Not Found"+studentId));

        long totalDays=attendanceRepository.countByStudentId(studentId);

        long presentDays=attendanceRepository.countByStudentIdAndStatus(studentId,"PRESENT");

        long abscentDays=attendanceRepository.countByStudentIdAndStatus(studentId,"ABSCENT");

        double percentage=totalDays==0?0.0:(presentDays*100.0)/totalDays;

        Map<String,Object> summary=new HashMap<>();
        summary.put("studentId",studentId);
        summary.put("totalDays",totalDays);
        summary.put("presentDays",presentDays);
        summary.put("abscentDays",abscentDays);
        summary.put("attendancePercentage",Math.round(percentage*100.0)/100.0);

        return summary;
    }
}

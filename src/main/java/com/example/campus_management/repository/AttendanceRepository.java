package com.example.campus_management.repository;

import com.example.campus_management.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import java.util.*;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    List<Attendance> findByStudentId(Long studentId);

    long countByStudentIdAndStatus(Long studentId, String status);

    long countByStudentId(Long studentId);
}

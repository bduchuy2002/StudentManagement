package com.example.studentmanagement.repository;

import com.example.studentmanagement.dto.response.StudentResponseDTO;
import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT s FROM Student s WHERE s.className = ?1")
    List<Student> findAllStudentInClass(String className);

    @Query(value = "select students.*, avg(scores.score) from students " +
            "inner join scores on scores.student_id = students.id " +
            "where students.class_name = ?1 group by students.id", nativeQuery = true)
    Map<Student, Double> getAVGScoreAllStudentInClass(String className);
}

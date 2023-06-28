package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.request.StudentDTO;
import com.example.studentmanagement.dto.response.StudentResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {
    List<StudentResponseDTO> getAll();
    Optional<StudentResponseDTO> findById(Long id);
    StudentResponseDTO save(StudentDTO studentDTO);
    StudentResponseDTO update(StudentDTO studentDTO, Long id);
    String delete(Long id);

    List<StudentResponseDTO> getStudentInClass(StudentDTO dto);

//    Map<StudentResponseDTO, Double> getAVGScoreAllStudentInClass(StudentDTO dto);
}

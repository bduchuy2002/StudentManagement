package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.request.SubjectDTO;
import com.example.studentmanagement.dto.response.SubjectResponseDTO;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Optional<SubjectResponseDTO> findById(Long id);
    List<SubjectResponseDTO> getAll();
    SubjectResponseDTO save (SubjectDTO dto);
    SubjectResponseDTO update (SubjectDTO dto, Long id);
}

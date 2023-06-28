package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.request.ScoreDTO;
import com.example.studentmanagement.dto.response.ScoreResponseDTO;

import java.util.List;

public interface ScoreService {
    ScoreResponseDTO save(ScoreDTO scoreDTO, Long idUser);
    ScoreResponseDTO update(ScoreDTO scoreDTO, Long id);
    String delete(Long idStudent, String subject);
    List<ScoreResponseDTO> getScoreByStudent(Long id);
    List<ScoreResponseDTO> findAllScoreInSubject(Long id);
    List<ScoreResponseDTO> findFiveScoreHighestInSubject(Long id);
    Double averageScoreStudent(Long id);
}

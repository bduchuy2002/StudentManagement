package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.request.ScoreDTO;
import com.example.studentmanagement.dto.response.ScoreResponseDTO;
import com.example.studentmanagement.entity.Score;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.Subject;
import com.example.studentmanagement.error.ResourceNotFoundException;
import com.example.studentmanagement.repository.ScoreRepository;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.repository.SubjectRepository;
import com.example.studentmanagement.service.ScoreService;
import com.example.studentmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private final ScoreRepository scoreRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    @Override
    public ScoreResponseDTO save(ScoreDTO scoreDTO, Long idStudent) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + idStudent));

        Subject subject = subjectRepository.findById(scoreDTO.getIdSubject())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + scoreDTO.getIdSubject()));

        Score score = new Score();
        score.setScore(scoreDTO.getScore());
        score.setStudents(student);
        score.setSubject(subject);
        return mapperToScoreDTO(scoreRepository.save(score));
    }

    @Override
    public ScoreResponseDTO update(ScoreDTO scoreDTO, Long idStudent) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + idStudent));
        Subject subject = subjectRepository.findById(scoreDTO.getIdSubject())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + scoreDTO.getIdSubject()));
        Score score = new Score();
        score.setScore(scoreDTO.getScore());
        score.setSubject(subject);
        score.setStudents(student);
        return mapperToScoreDTO(scoreRepository.save(score));
    }

    @Override
    public String delete(Long idStudent, String subject) {
        return null;
    }

    @Override
    public List<ScoreResponseDTO> getScoreByStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        List<Score> scoresOfStudent = scoreRepository.getScoreByStudent(id);
        return scoresOfStudent.stream().map(c -> mapperToScoreDTO(c)).collect(Collectors.toList());
    }

    @Override
    public List<ScoreResponseDTO> findAllScoreInSubject(Long id) {
        subjectRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Subject not found with id: " + id));
        List<Score> scoreList = scoreRepository.getAllScoreInSubject(id);
        return scoreList.stream().map(c-> mapperToScoreDTO(c)).collect(Collectors.toList());
    }

    @Override
    public List<ScoreResponseDTO> findFiveScoreHighestInSubject(Long id) {
        subjectRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Subject not found with id: " + id));
        List<Score> scoreList = scoreRepository.getFiveScoreHighestInSubject(id);
        return scoreList.stream().map(c-> mapperToScoreDTO(c)).collect(Collectors.toList());
    }

    @Override
    public Double averageScoreStudent(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return scoreRepository.averageScoreStudent(id);
    }

    private ScoreResponseDTO mapperToScoreDTO(Score score) {
        ScoreResponseDTO dto = new ScoreResponseDTO();
        dto.setScore(score.getScore());
        dto.setStudent(score.getStudents());
        dto.setSubjectName(score.getSubject().getName());
        return dto;
    }
}

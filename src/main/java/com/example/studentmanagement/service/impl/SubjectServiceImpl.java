package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.request.SubjectDTO;
import com.example.studentmanagement.dto.response.ScoreResponseDTO;
import com.example.studentmanagement.dto.response.SubjectResponseDTO;
import com.example.studentmanagement.entity.Score;
import com.example.studentmanagement.entity.Subject;
import com.example.studentmanagement.error.ResourceNotFoundException;
import com.example.studentmanagement.repository.SubjectRepository;
import com.example.studentmanagement.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    @Override
    public Optional<SubjectResponseDTO> findById(Long id) {
        subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        return Optional.of(mapperToSubjectDTO(subjectRepository.findById(id).get()));
    }
    @Override
    public List<SubjectResponseDTO> getAll() {
        return subjectRepository.findAll().stream().map(p -> mapperToSubjectDTO(p)).collect(Collectors.toList());
    }
    @Override
    public SubjectResponseDTO save(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setName(dto.getSubjectName());
        return mapperToSubjectDTO(subjectRepository.save(subject));
    }
    @Override
    public SubjectResponseDTO update(SubjectDTO dto, Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        subject.setName(dto.getSubjectName());
        return mapperToSubjectDTO(subjectRepository.save(subject));
    }

    private SubjectResponseDTO mapperToSubjectDTO(Subject subject) {
        SubjectResponseDTO dto = new SubjectResponseDTO();
        dto.setSubjectName(subject.getName());
        dto.setId(subject.getId());
        return dto;
    }
}

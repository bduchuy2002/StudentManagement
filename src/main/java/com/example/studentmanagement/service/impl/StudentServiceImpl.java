package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.request.StudentDTO;
import com.example.studentmanagement.dto.response.StudentResponseDTO;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.error.ResourceNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public List<StudentResponseDTO> getAll() {
        return studentRepository.findAll().stream().map(p -> mapperToStudentDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentResponseDTO> findById(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return Optional.of(mapperToStudentDTO(studentRepository.findById(id).get()));
    }

    @Override
    public StudentResponseDTO save(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setPhone(studentDTO.getPhone());
        student.setEmail(studentDTO.getEmail());
        student.setStartDate(studentDTO.getStartDate());
        student.setAddress(studentDTO.getAddress());
        student.setClassName(studentDTO.getClassName());

        Student saved = studentRepository.save(student);
        return mapperToStudentDTO(saved);
    }

    @Override
    public StudentResponseDTO update(StudentDTO studentDTO, Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());
        student.setStartDate(studentDTO.getStartDate());
        student.setClassName(studentDTO.getClassName());
        return mapperToStudentDTO(studentRepository.save(student));
    }

    @Override
    public String delete(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.deleteById(id);
        return "Delete Successful";
    }

    @Override
    public List<StudentResponseDTO> getStudentInClass(StudentDTO dto) {
        List<Student> students = studentRepository.findAllStudentInClass(dto.getClassName());
        return students.stream().map(c -> mapperToStudentDTO(c)).collect(Collectors.toList());
    }

//    @Override
//    public Map<StudentResponseDTO, Double> getAVGScoreAllStudentInClass(StudentDTO dto) {
//        Map<Student, Double> listScore = studentRepository.getAVGScoreAllStudentInClass(dto.getClassName());
////        return listScore.stream().map(c -> mapperToStudentDTO(c)).collect(Collectors.toList());
//        return listScore.
//    }

    private StudentResponseDTO mapperToStudentDTO(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setName(student.getName());
        dto.setPhone(student.getPhone());
        dto.setEmail(student.getEmail());
        dto.setStartDate(student.getStartDate());
        dto.setAddress(student.getAddress());
        dto.setStudentCode(student.getStudentCode());
        dto.setClassName(student.getClassName());
        return dto;
    }
}

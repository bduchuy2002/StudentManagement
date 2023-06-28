package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.request.StudentDTO;
import com.example.studentmanagement.dto.request.SubjectDTO;
import com.example.studentmanagement.dto.response.StudentResponseDTO;
import com.example.studentmanagement.dto.response.SubjectResponseDTO;
import com.example.studentmanagement.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(subjectService.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<SubjectResponseDTO> create(@RequestBody SubjectDTO dto) {
        return new ResponseEntity<>(subjectService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<SubjectResponseDTO> update(@RequestParam("id") Long id, @RequestBody SubjectDTO dto) {
        return new ResponseEntity<>(subjectService.update(dto, id), HttpStatus.OK);
    }
}

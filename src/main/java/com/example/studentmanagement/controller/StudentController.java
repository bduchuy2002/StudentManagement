package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.request.StudentDTO;
import com.example.studentmanagement.dto.response.StudentResponseDTO;
import com.example.studentmanagement.service.StudentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<StudentResponseDTO> create(@RequestBody StudentDTO dto) {
        return new ResponseEntity<>(studentService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<StudentResponseDTO> update(@RequestParam("id") Long id, @RequestBody StudentDTO dto) {
        return new ResponseEntity<>(studentService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> findAllStudentInClass(@RequestBody StudentDTO dto) {
        return new ResponseEntity<>(studentService.getStudentInClass(dto), HttpStatus.OK);
    }

//    @PostMapping("/avgs")
//    public ResponseEntity<Object> getAVGScoreInClass(@RequestBody StudentDTO dto) {
//        return new ResponseEntity<>(studentService.getAVGScoreAllStudentInClass(dto), HttpStatus.OK);
//    }
}

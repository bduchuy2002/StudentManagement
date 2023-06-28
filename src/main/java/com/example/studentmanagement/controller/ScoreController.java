package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.request.ScoreDTO;
import com.example.studentmanagement.dto.request.StudentDTO;
import com.example.studentmanagement.dto.response.ScoreResponseDTO;
import com.example.studentmanagement.dto.response.StudentResponseDTO;
import com.example.studentmanagement.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/score")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;
    @PostMapping("")
    public ResponseEntity<ScoreResponseDTO> create(@RequestParam("id") Long id, @RequestBody ScoreDTO dto) {
        return new ResponseEntity<>(scoreService.save(dto, id), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<ScoreResponseDTO> update(@RequestParam("id") Long id, @RequestBody ScoreDTO dto) {
        return new ResponseEntity<>(scoreService.update(dto, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllScoresStudent(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scoreService.getScoreByStudent(id), HttpStatus.OK);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Object> getAllScoresInSubject(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scoreService.findAllScoreInSubject(id), HttpStatus.OK);
    }

    @GetMapping("/best/{id}")
    public ResponseEntity<Object> getBestScoresInSubject(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scoreService.findFiveScoreHighestInSubject(id), HttpStatus.OK);
    }

    @GetMapping("/avg/{id}")
    public ResponseEntity<Double> getAVGScoreStudent(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scoreService.averageScoreStudent(id), HttpStatus.OK);
    }
}

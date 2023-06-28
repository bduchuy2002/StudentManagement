package com.example.studentmanagement.dto.response;

import com.example.studentmanagement.entity.Student;
import lombok.Data;

@Data
public class ScoreResponseDTO {
    private String subjectName;
    private double score;
    private Student student;

}

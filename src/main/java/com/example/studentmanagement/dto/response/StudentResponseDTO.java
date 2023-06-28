package com.example.studentmanagement.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponseDTO {
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate startDate;
    private String studentCode;
    private String className;
}

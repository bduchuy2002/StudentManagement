package com.example.studentmanagement.dto.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate startDate;
    private String className;
}

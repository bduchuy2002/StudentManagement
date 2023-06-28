package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.Join;

import java.time.LocalDate;
import java.util.Random;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    private String phone;
    private String address;
    @Column(name = "student_code", unique = true)
    private String studentCode;

    private String className;
    @Column(name = "start_date")
    private LocalDate startDate;

    @PrePersist
    private void generateStudentID() {
        String year = String.valueOf(getStartDate().getYear());
        String randNum = String.format("%04d", new Random().nextInt(10000));
        this.studentCode = year + randNum;
    }
}

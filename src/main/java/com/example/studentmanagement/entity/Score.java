package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scores", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "subject_id"}))
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "subject_name")
//    private String subjectName;
    private double score;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student students;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}

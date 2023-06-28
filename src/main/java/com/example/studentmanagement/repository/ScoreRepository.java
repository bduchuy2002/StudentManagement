package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query(value = "SELECT * FROM scores WHERE student_id = :idStudent", nativeQuery = true)
    List<Score> getScoreByStudent(@Param("idStudent") long idStudent);
    //all score in subject
    @Query(value = "SELECT * FROM scores WHERE subject_id = :idSubject", nativeQuery = true)
    List<Score> getAllScoreInSubject(@Param("idSubject") Long idSubject);
    // top 5 score in subject
    @Query(value = "SELECT * FROM scores WHERE subject_id = :idSubject ORDER BY score DESC LIMIT 5", nativeQuery = true)
    List<Score> getFiveScoreHighestInSubject(@Param("idSubject") Long idSubject);

    @Query(value = "SELECT AVG(score) AS average_score FROM scores WHERE student_id = :idStudent", nativeQuery = true)
    Double averageScoreStudent(@Param("idStudent") Long idStudent);


}

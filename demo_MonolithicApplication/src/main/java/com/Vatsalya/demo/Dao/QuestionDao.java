package com.Vatsalya.demo.Dao;

import com.Vatsalya.demo.model.questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<questions,Integer> {

    List<questions> findByCategory(String category);
    @Query(value="SELECT * FROM questions q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery=true)
    List<questions> findRandomQuestionsByCategory(String category, int numQ);
}

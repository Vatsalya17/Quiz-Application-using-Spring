package com.microServices.question_Service.Dao;

import com.microServices.question_Service.model.questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<questions,Integer> {

    List<questions> findByCategory(String category);
    @Query(value="SELECT q.id FROM questions q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery=true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);


}

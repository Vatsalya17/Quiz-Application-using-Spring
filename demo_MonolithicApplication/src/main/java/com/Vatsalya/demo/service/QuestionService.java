package com.Vatsalya.demo.service;

import com.Vatsalya.demo.Dao.QuestionDao;
import com.Vatsalya.demo.model.questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionService QuestionDao;
@Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<questions>> getAllQuestions() {
try {
    return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
} catch (Exception e) {
    e.printStackTrace();
}
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<questions>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public String addQuestion(questions question) {
       questionDao.save(question);
       return "success";
    }
}

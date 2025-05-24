package com.example.QuizService.service;


import com.example.QuizService.Dao.QuizDao;
import com.example.QuizService.Feign.QuizInterface;
import com.example.QuizService.model.QuestionWrapper;
import com.example.QuizService.model.Quiz;
import com.example.QuizService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
      List<Integer> questions = quizInterface.generateQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
      Optional<Quiz> quiz = quizDao.findById(id);
      List<Integer> questionIds = quiz.get().getQuestionIds();
      ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId((questionIds));
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score= quizInterface.getScore(responses);
        return score;
    }
}

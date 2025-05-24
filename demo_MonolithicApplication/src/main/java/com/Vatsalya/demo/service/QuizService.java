package com.Vatsalya.demo.service;

import com.Vatsalya.demo.Dao.QuestionDao;
import com.Vatsalya.demo.Dao.QuizDao;
import com.Vatsalya.demo.model.QuestionWrapper;
import com.Vatsalya.demo.model.Quiz;
import com.Vatsalya.demo.model.Response;
import com.Vatsalya.demo.model.questions;
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
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<questions> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz = quizDao.findById(id);
       List<questions> questionsFromDB= quiz.get().getQuestions();
       List<QuestionWrapper> questionsForUser = new ArrayList<>();
       for(questions q:questionsFromDB)
       {
           QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           questionsForUser.add(qw);
       }
       return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<questions> questions= quiz.get().getQuestions();
        int i=0;
        int right=0;
        for(Response re: responses)
        {
            if(re.getResponse().equals(questions.get(i).getRightAnswer()))
            {
               right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}

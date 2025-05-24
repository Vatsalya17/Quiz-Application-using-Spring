package com.microServices.question_Service.service;


import com.microServices.question_Service.Dao.QuestionDao;
import com.microServices.question_Service.model.QuestionWrapper;
import com.microServices.question_Service.model.Response;
import com.microServices.question_Service.model.questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<questions> questions =new ArrayList<>();
        for(Integer id : questionIds)
        {
             questions.add(questionDao.findById(id).get());
        }
        for(questions question : questions)
        {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }


    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        for(Response response: responses)
        {
            questions question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
            {
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}

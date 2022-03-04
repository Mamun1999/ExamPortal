package com.mamun.exam.service.impl;

import java.util.HashSet;
import java.util.Set;

import com.mamun.exam.model.exam.Question;
import com.mamun.exam.model.exam.Quiz;
import com.mamun.exam.repository.QuestionRepository;
import com.mamun.exam.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class QuestionServiceImpl implements QuestionService {
   
    @Autowired
   private QuestionRepository questionRepository;


    @Override
    public Question addQuestion(Question questiion) {
       
        return this.questionRepository.save(questiion);
    }

    @Override
    public Question updateQuestion(Question question) {
        
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
      
        return new HashSet<>(this.questionRepository.findAll()) ;
    }

    @Override
    public Question getQuestion(Long questionId) {
        
        return this.questionRepository.findById(questionId).get();

    }

    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
      
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long quesid) {
     Question question=  new Question();
     question.setQuesid(quesid);
     this.questionRepository.delete(question);
        
    }

    @Override
    public Question get(Long questionsId) {// it will give questions with ans from database
        
        return this.questionRepository.getOne(questionsId);
        
    }
    
}

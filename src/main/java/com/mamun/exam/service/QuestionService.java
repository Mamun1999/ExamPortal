package com.mamun.exam.service;

import java.util.Set;

import com.mamun.exam.model.exam.Question;
import com.mamun.exam.model.exam.Quiz;

public interface QuestionService {
    
    public Question addQuestion(Question questiion);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestions();

    public Question getQuestion(Long questionId);
//for getting set of questions from quiz
    public Set<Question> getQuestionsOfQuiz(Quiz quiz);

    public void deleteQuestion(Long quesid);

    public Question get(Long questionsId);

    
}

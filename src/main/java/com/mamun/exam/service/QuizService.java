package com.mamun.exam.service;

import java.util.List;
import java.util.Set;

import com.mamun.exam.model.exam.Category;
import com.mamun.exam.model.exam.Quiz;



public interface QuizService {
    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);
//all quiz
    public Set<Quiz> getQuizzes();
//single quiz
    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);

    public List<Quiz> getQuizzesOfCategory(Category category);
    public List<Quiz>   getActiveQuizzes();
    public List<Quiz> getActiveQuizzesOfCategory(Category c);   
}

package com.mamun.exam.repository;

import java.util.Set;

import com.mamun.exam.model.exam.Question;
import com.mamun.exam.model.exam.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long>{

    Set<Question> findByQuiz(Quiz quiz);//got Quiz(findByQuiz) from Question class quiz
    
}

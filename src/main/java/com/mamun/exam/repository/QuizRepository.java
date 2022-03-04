package com.mamun.exam.repository;

import java.util.List;

import com.mamun.exam.model.exam.Category;
import com.mamun.exam.model.exam.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long>{

    public List<Quiz> findBycategory(Category category);

    public List<Quiz> findByActive(Boolean b);//these are custom finder method

    public List<Quiz> findByCategoryAndActive(Category c,Boolean b);
    
}

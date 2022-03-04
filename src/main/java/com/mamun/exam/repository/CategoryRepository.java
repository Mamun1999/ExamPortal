package com.mamun.exam.repository;

import com.mamun.exam.model.exam.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    
}

package com.mamun.exam.service;

import java.util.Set;

import com.mamun.exam.model.exam.Category;

public interface CategoryService {
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    //getting all categories
    public Set<Category> getCategories();
    //getting single category
    public Category getCategory(Long categoryId);

    public void deleteCategory(Long categoryId);


}

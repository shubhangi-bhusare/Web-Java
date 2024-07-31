package com.blogs.service;

import java.util.List;

import com.blogs.entities.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	Category addNewCategory(Category newCategory);
	String deleteCategoryDetails(Long catId);
	Category getCategoryDetails(Long categoryId);
	Category updateCategoryDetails(Category category);
}

package com.blogs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogs.custom_exceptions.ResourceNotFoundException;
import com.blogs.entities.Category;
import com.blogs.repository.CategoryRepository;

@Service // spring bean containing B.L
@Transactional // auto tx management
public class CategoryServiceImpl implements CategoryService {
	// depcy - dao layer i/f
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category addNewCategory(Category newCategory) {
		// use the method inherited from CrudRepository
		// public T save(T entity)
		return categoryRepository.save(newCategory);
	}

	@Override
	public String deleteCategoryDetails(Long catId) {
		if (categoryRepository.existsById(catId)) {
			// API of CrudRepo - public void deleteById(ID id)
			categoryRepository.deleteById(catId);
			return "category details deleted";
		}
		return "deleting category details failed : Invalid category ID";
	}

	@Override
	public Category getCategoryDetails(Long categoryId) {
		// inherited method - Optional<Catgeory> findById(Long catId)
		Optional<Category> optional = categoryRepository.findById(categoryId);
		
		return optional.orElseThrow(() -> 
		new ResourceNotFoundException("Invalid Category ID!!!!"));
	}

	@Override
	public Category updateCategoryDetails(Category category) {
		// CrudRepo : save 
		return categoryRepository.save(category);
	}
	
	
	

}

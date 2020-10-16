package com.poshaque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poshaque.dao.CategoryRepository;
import com.poshaque.model.Categories;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Categories> getAllCategories(){
		return categoryRepository.findAll();
	}
}

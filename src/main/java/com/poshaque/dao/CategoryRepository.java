package com.poshaque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poshaque.model.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer>{

}

package com.poshaque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poshaque.dao.ProductsRepository;
import com.poshaque.model.Products;

@Service
public class ProductService {
	
	@Autowired
	private ProductsRepository productsRepository;

	public Page<Products> getAllProducts(Pageable page){
		return productsRepository.findAll(page);
	}

	public Page<Products> getProductsByCategoryId(Integer categoryId, Pageable page) {
		return productsRepository.findByCategoryId(categoryId,page);
	}
}

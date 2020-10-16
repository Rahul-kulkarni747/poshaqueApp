package com.poshaque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.model.Products;
import com.poshaque.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;

	@GetMapping
	@RequestMapping("/all")
	public Page<Products> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "20") Integer pageSize,
			@RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "ASC") String orderBy){
		Pageable page = orderBy.toUpperCase().equals("DESC")?PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy)):
			PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
		return productService.getAllProducts(page);
	}
	
	@GetMapping
	@RequestMapping("/category_id")
	public Page<Products> getProductsByCategoryId(@RequestParam Integer categoryId,
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "20") Integer pageSize,
			@RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "ASC") String orderBy){
		Pageable page = orderBy.toUpperCase().equals("DESC")?PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy)):
			PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
		return productService.getProductsByCategoryId(categoryId,page);
	}
}

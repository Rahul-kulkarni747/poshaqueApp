package com.poshaque.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.model.ProductThumbnail;
import com.poshaque.model.Products;
import com.poshaque.service.ProductService;
import com.poshaque.service.UserPrincipal;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;

	@GetMapping
	@RequestMapping("/all")
	public Page<Products> getAllProducts(@RequestParam(defaultValue = "0") String pageNo,
			@RequestParam(defaultValue = "20") String pageSize,@RequestParam(defaultValue = "") String searchTerm,
			@RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "ASC") String orderBy){
		Pageable page = orderBy.toUpperCase().equals("DESC")?PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, sortBy)):
			PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.ASC, sortBy));
		return productService.getAllProducts(page,searchTerm);
	}
	
	@GetMapping
	@RequestMapping("/category_id")
	public Page<Products> getProductsByCategoryId(@RequestParam String id,
			@RequestParam(defaultValue = "0") String pageNo, @RequestParam(defaultValue = "") String searchTerm,
			@RequestParam(defaultValue = "20") String pageSize,
			@RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "ASC") String orderBy){
		Pageable page = orderBy.toUpperCase().equals("DESC")?PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, sortBy)):
			PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.ASC, sortBy));
		return productService.getProductsByCategoryId(Integer.parseInt(id),page,searchTerm);
	}
	
	@GetMapping
	public Map<String,Object> getProductById(@RequestParam Integer id, @AuthenticationPrincipal UserPrincipal principal){
		return  productService.getProductById(id,principal);
	}
	
	@GetMapping("/cart_thumbnails")
	public List<ProductThumbnail> getCartThumbnails(@AuthenticationPrincipal UserPrincipal principal){
		return productService.getCartThumbnails(principal);
	}
	
}

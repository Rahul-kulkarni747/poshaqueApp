package com.poshaque.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.poshaque.model.Products;

public interface ProductsRepository extends PagingAndSortingRepository<Products, Integer>{
	
	@Query(value = "select * from Products where category_id =:categoryId",
			countQuery = "select * from Products where category_id =:categoryId", 
			nativeQuery = true)
	Page<Products> findByCategoryId(@Param("categoryId") int userId, Pageable page);

	
}

package com.poshaque.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.poshaque.model.Products;

public interface ProductsRepository extends PagingAndSortingRepository<Products, Integer>{
	
	@Query(value = "select * from Products p where category_id =:categoryId",
			countQuery = "select * from Products where category_id =:categoryId", 
			nativeQuery = true)
	Page<Products> findByCategoryId(@Param("categoryId") int catId, Pageable page);
	
	@Query(value = "select *  from Products p where name like %:searchTerm%",
			countQuery = "select * from Products where name like %:searchTerm%", 
			nativeQuery = true)
	Page<Products> findAll(Pageable page, @Param("searchTerm")  String searchTerm);
	
	@Query(value = "select * from Products p where category_id =:categoryId and name like %:searchTerm%",
			countQuery = "select * from Products where category_id =:categoryId and name like %:searchTerm%", 
			nativeQuery = true)
	Page<Products> findByCategoryId(@Param("categoryId") int catId, Pageable page, @Param("searchTerm")  String searchTerm);
	
	@Query(value = "select * from Products p where category_id =:categoryId and id!=:prodId order by RAND() limit 4",
			nativeQuery = true)
	List<Products> findRandomProducts(@Param("categoryId") Integer catId, @Param("prodId") Integer prodId);

	
}

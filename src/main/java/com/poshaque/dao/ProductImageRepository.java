package com.poshaque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poshaque.model.ProductImages;

public interface ProductImageRepository extends JpaRepository<ProductImages, Integer> {

	@Query(value = "select * from ProductImages where product_id =:productId limit 1",
			nativeQuery = true)
	List<ProductImages> findOneByProductId(@Param("productId") int proId);

	List<ProductImages> findByProductId(Integer id);

}

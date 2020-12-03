package com.poshaque.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.poshaque.model.Reviews;

public interface ReviewRepository extends PagingAndSortingRepository<Reviews, Integer> {
	
	@Query(value = "select r.rating,r.review_description,r.created_on,r.likes,r.dislikes,"
			+ "CONCAT(u.first_name,' ',u.last_name) as user_info, CONCAT(SUBSTRING(u.first_name,1,1),SUBSTRING(u.last_name,1,1)) as user_logo "
			+ "from reviews r join user u on u.id = r.user_id where product_id =:productId",
			countQuery = "select * from reviews where product_id =:productId", 
			nativeQuery = true)
	Page<Map<String,Object>> findByProductId(@Param("productId") int productId, Pageable page);
	
	@Query(value = "select avg(rating) as rating, count(*) as ratecount from reviews where product_id = :prodId", 
			nativeQuery = true)
	Map<String,String> findReviewCount(@Param("prodId") int productId);
	
	@Query(value = "select count(*) as revcount from reviews where product_id = :prodId and user_id = :userid", 
			nativeQuery = true)
	Integer findUserReviewCount(@Param("prodId") int productId, @Param("userid") int userId);
}

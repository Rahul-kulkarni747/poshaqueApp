package com.poshaque.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poshaque.model.Cart;

public interface CartRepository  extends JpaRepository<Cart, Integer>{
	
	Cart findByUserId(int userId);

	@Query(value="delete from cart where user_id = :id",nativeQuery=true)
	@Transactional
	@Modifying
	void removeByUserId(@Param("id") Integer userId);
}

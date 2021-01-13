package com.poshaque.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poshaque.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	List<Orders> findByUserIdAndStatus(int userId, String status);
	
	@Transactional
	@Modifying
	@Query(value="update orders set has_active_transaction = 0, status = 'FAILED' where user_id = :id and status='PENDING'",nativeQuery=true)
	void updateOrderStatus(@Param("id") Integer userId);
	
	@Transactional
	@Modifying
	@Query(value="update orders set status = 'CANCELLED' where id = :id",nativeQuery=true)
	void cancelOrder(@Param("id") Integer orderId);
	
	Optional<Orders> findByClientOrderId(String object);
	
	@Query(value = "select * from orders where user_id = :userId and status in ('ACCEPTED','DISPATCHED','OUT_FOR_DELIVERY')",
			countQuery = "select * from orders where user_id = :userId and status in ('ACCEPTED','DISPATCHED','OUT_FOR_DELIVERY')", 
			nativeQuery = true)
	Page<Orders> findCurrentOrdersForUser(@Param("userId") Integer id, Pageable page);

	@Query(value = "select * from orders where user_id = :userId and status in ('DELIVERED','CANCELLED')",
			countQuery = "select * from orders where user_id = :userId and status in ('DELIVERED','CANCELLED')", 
			nativeQuery = true)
	Page<Orders> findOldOrdersForUser(@Param("userId") Integer id, Pageable page);
}

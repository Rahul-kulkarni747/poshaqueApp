package com.poshaque.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poshaque.dao.ReviewRepository;
import com.poshaque.exception.PoshaqueBussinessException;
import com.poshaque.model.Reviews;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	public Page<Map<String,Object>> getReviewsByProductId(int productId, Pageable page){
		return reviewRepository.findByProductId(productId, page);
	}

	public Reviews saveReview(Reviews reviews,UserPrincipal principal) {
		Integer userReviewCount = reviewRepository.findUserReviewCount(reviews.getProductId(), principal.getId());
		
		if(userReviewCount > 0)
			throw new PoshaqueBussinessException("User Already Reviewed");
		
		reviews.setUserId(principal.getId());
		reviews.setCreatedOn(new Date());
		return reviewRepository.save(reviews);
	}
}

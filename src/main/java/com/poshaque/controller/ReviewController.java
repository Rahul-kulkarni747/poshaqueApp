package com.poshaque.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.model.Reviews;
import com.poshaque.service.ReviewService;
import com.poshaque.service.UserPrincipal;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public Page<Map<String,Object>> getReviewsByProductId(@RequestParam String id,
			@RequestParam(defaultValue = "0") String pageNo,
			@RequestParam(defaultValue = "5") String pageSize,
			@RequestParam(defaultValue = "created_on") String sortBy, @RequestParam(defaultValue = "DESC") String orderBy){
		Pageable page = orderBy.toUpperCase().equals("DESC")?PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, sortBy)):
			PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.ASC, sortBy));
		return reviewService.getReviewsByProductId(Integer.parseInt(id),page);
	}
	
	@PostMapping
	public Reviews saveReview(@RequestBody Reviews reviews, @AuthenticationPrincipal UserPrincipal principal){
		return reviewService.saveReview(reviews,principal);
	}
}

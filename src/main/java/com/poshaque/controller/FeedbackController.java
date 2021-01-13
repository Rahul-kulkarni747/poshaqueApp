package com.poshaque.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.model.Feedback;
import com.poshaque.service.FeedbackService;
import com.poshaque.service.UserPrincipal;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	@PostMapping
	public Feedback saveFeedback(@RequestBody Map<String,Object> map,@AuthenticationPrincipal UserPrincipal principal){
		return feedbackService.saveFeedback(map,principal);
	}
}

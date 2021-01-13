package com.poshaque.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poshaque.dao.FeedbackRepository;
import com.poshaque.model.Feedback;

@Service
public class FeedbackService {
	
	@Autowired
	FeedbackRepository feedbackRepository;

	public Feedback saveFeedback(Map<String, Object> map, UserPrincipal principal) {
		Feedback feedback = new Feedback(principal.getId(), map.get("feedback").toString());
		return feedbackRepository.save(feedback);
	}

}

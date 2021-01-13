package com.poshaque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poshaque.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}

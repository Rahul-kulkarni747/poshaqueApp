package com.poshaque.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reviews {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int productId;
	private int rating;
	private String reviewDescription;
	private int userId;
	private Date createdOn;
	private int likes;
	private int dislikes;

	public Reviews() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reviews(int id, int productId, int rating, String review_description, int userId, Date createdOn, int likes,
			int dislikes) {
		super();
		this.id = id;
		this.productId = productId;
		this.rating = rating;
		this.reviewDescription = review_description;
		this.userId = userId;
		this.createdOn = createdOn;
		this.likes = likes;
		this.dislikes = dislikes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String review_description) {
		this.reviewDescription = review_description;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

}

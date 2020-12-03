package com.poshaque.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private String cartData;
	private Date updatedOn;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int id, int userId, String cartData, Date updatedOn) {
		super();
		this.id = id;
		this.userId = userId;
		this.cartData = cartData;
		this.updatedOn = updatedOn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCartData() {
		return cartData;
	}

	public void setCartData(String cartData) {
		this.cartData = cartData;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}

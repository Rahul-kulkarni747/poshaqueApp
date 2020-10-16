package com.poshaque.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String pinValue;
	private Date createdOn;
	private String userEmail;

	public Pin() {
		super();
	}
	
	public Pin(int id,String pinValue, Date createdOn, String userEmail) {
		super();
		this.id = id;
		this.pinValue = pinValue;
		this.createdOn = createdOn;
		this.userEmail = userEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPinValue() {
		return pinValue;
	}

	public void setPinValue(String pinValue) {
		this.pinValue = pinValue;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUserId() {
		return userEmail;
	}

	public void setUserId(String userEmail) {
		this.userEmail = userEmail;
	}

}

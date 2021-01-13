package com.poshaque.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private String orderDetails;
	private String totalPrice;
	private Date createdOn;
	private boolean paymentStatus;
	private boolean hasActiveTransaction;
	private String status;
	private String clientOrderId;
	private String receiptId;
	private String clientPaymentId;
	private int addressId;
	@JsonIgnore
	private String clientSignature;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(int userId, String orderDetails, String totalPrice, Date createdOn, boolean paymentStatus,
			boolean hasActiveTransaction, String status, int addressId) {
		super();
		this.userId = userId;
		this.orderDetails = orderDetails;
		this.totalPrice = totalPrice;
		this.createdOn = createdOn;
		this.paymentStatus = paymentStatus;
		this.hasActiveTransaction = hasActiveTransaction;
		this.status = status;
		this.addressId = addressId;
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

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public boolean isHasActiveTransaction() {
		return hasActiveTransaction;
	}

	public void setHasActiveTransaction(boolean hasActiveTransaction) {
		this.hasActiveTransaction = hasActiveTransaction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getClientPaymentId() {
		return clientPaymentId;
	}

	public void setClientPaymentId(String clientPaymentId) {
		this.clientPaymentId = clientPaymentId;
	}

	public String getClientSignature() {
		return clientSignature;
	}

	public void setClientSignature(String clientSignature) {
		this.clientSignature = clientSignature;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
}

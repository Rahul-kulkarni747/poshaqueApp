package com.poshaque.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String productId;
	@Transient
	private List<ProductImages> productImage;
	private String image;
	private String description;
	private int categoryId;
	private String tags;
	private int totalSold;
	private int price;
	public Date createdOn;
	public boolean hasSizes;
	public boolean isGenderSpecific;
	public boolean isPriceDifferent;
	public String priceSizeJson;
	public String availableSizes;
	public String productThumbnail;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(int id, String name, String productId, List<ProductImages> productImage, String image,
			String description, int categoryId, String tags, int totalSold, int price, Date createdOn, boolean hasSizes,
			boolean isGenderSpecific, boolean isPriceDifferent, String priceSizeJson, String availableSizes, String productThumbnail) {
		super();
		this.id = id;
		this.name = name;
		this.productId = productId;
		this.productImage = productImage;
		this.image = image;
		this.description = description;
		this.categoryId = categoryId;
		this.tags = tags;
		this.totalSold = totalSold;
		this.price = price;
		this.createdOn = createdOn;
		this.hasSizes = hasSizes;
		this.isGenderSpecific = isGenderSpecific;
		this.isPriceDifferent = isPriceDifferent;
		this.priceSizeJson = priceSizeJson;
		this.availableSizes = availableSizes;
		this.productThumbnail = productThumbnail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<ProductImages> getProductImage() {
		return productImage;
	}

	public void setProductImage(List<ProductImages> productImage) {
		this.productImage = productImage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getTotalSold() {
		return totalSold;
	}

	public void setTotalSold(int totalSold) {
		this.totalSold = totalSold;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public boolean isHasSizes() {
		return hasSizes;
	}

	public void setHasSizes(boolean hasSizes) {
		this.hasSizes = hasSizes;
	}

	public boolean isGenderSpecific() {
		return isGenderSpecific;
	}

	public void setGenderSpecific(boolean isGenderSpecific) {
		this.isGenderSpecific = isGenderSpecific;
	}

	public boolean isPriceDifferent() {
		return isPriceDifferent;
	}

	public void setPriceDifferent(boolean isPriceDifferent) {
		this.isPriceDifferent = isPriceDifferent;
	}

	public String getPriceSizeJson() {
		return priceSizeJson;
	}

	public void setPriceSizeJson(String priceSizeJson) {
		this.priceSizeJson = priceSizeJson;
	}

	public String getAvailableSizes() {
		return availableSizes;
	}

	public void setAvailableSizes(String availableSizes) {
		this.availableSizes = availableSizes;
	}

	public String getProductThumbnail() {
		return productThumbnail;
	}

	public void setProductThumbnail(String productThumbnail) {
		this.productThumbnail = productThumbnail;
	}
	
}

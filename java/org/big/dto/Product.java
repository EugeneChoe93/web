package org.big.dto;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 0;
	private String productId;
	private String name;
	private int price;
	private String category;
	private String description;
	private String releaseDate;
	private long soldout;
	private String filename;
	private int quantity;
	
	public Product() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Product(String productId, String name, int Price) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getSoldout() {
		return soldout;
	}

	public void setSoldout(long soldout) {
		this.soldout = soldout;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

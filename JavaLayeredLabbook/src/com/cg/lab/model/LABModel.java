package com.cg.lab.model;

public class LABModel {

	private String name;
	private String mail;
	private String phone;
	private int id;
	private String mobile;
	private int quantity;
	private Double price;
	private Double maxPrice;
	private Double minPrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LABModel(String name, String mail, String phone, int id,
			String mobile, int quantity, Double price, Double minPrice,
			Double maxPrice) {
		super();
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.id = id;
		this.mobile = mobile;
		this.quantity = quantity;
		this.price = price;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;

	}

	public LABModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LABModel [name=" + name + ", mail=" + mail + ", phone=" + phone
				+ ", id=" + id + ", mobile=" + mobile + ", quantity="
				+ quantity + ", price=" + price + ", maxPrice=" + maxPrice
				+ ", minPrice=" + minPrice + "]";
	}

}

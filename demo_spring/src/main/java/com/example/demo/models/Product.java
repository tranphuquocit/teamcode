package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity //entity map voi csdl
public class Product {

	
	@Id  //Khoa chinh + not null
	@GeneratedValue(strategy = GenerationType.AUTO)  //id auto+1 
	private Long id;
	private String productName;
	private String year;
	private Double price;
	private String desc;
	

	public Product() {
		super();
	}

	public Product(Long id, String productName, String year, Double price, String desc) {
		super();
		this.id = id;
		this.productName = productName;
		this.year = year;
		this.price = price;
		this.desc = desc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setproductName(String productName) {
		this.productName = productName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	};
	
	
}